package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.utils.ObjUtils;
import com.example.cloud.project.integrated.common.utils.StringTools;
import com.example.cloud.project.integrated.translate.dao.entity.TransformInformation;
import com.example.cloud.project.integrated.translate.dao.mapper.TransformInformationMapper;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.translate.holder.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:05
 */
@Service
public class TranslatorMangerService {
    @Autowired
    private TranslateCacheService translateCacheService;

    @Autowired
    private TransformInformationMapper translateMapper;
    @Autowired
    @Qualifier("StepExecutor")
    private ExecutorService executorService;

    public TranslateResponse translate(TranslateChannel translateChannel) {
        // 如果自定义了完整的映射，直接使用完整的映射返回
        TranslateResponse response = getTranslateInformation(translateChannel);
        if (ObjUtils.isNotEmpty(response)) {
            return response;
        }
        String source = translateChannel.getText();
        Translator translator = getTranslator(translateChannel);
        if (ObjUtils.isEmpty(translator)) {
            return null;
        }
        List<String> wordList = StringTools.split(source);
        String words = StringUtils.join(wordList, StringUtils.SPACE);
        translateChannel.setText(words);
        if("en-ch".equals(translateChannel.getType())){
            response = translator.en2Ch(translateChannel);
        }else if ("ch2en".equals(translateChannel.getType())){
            response = translator.ch2En(translateChannel);
        }else {
            return null;
        }
        if (response != null) {
            response.setTarget(response.getTarget().replace("的", ""));
            save(response,translateChannel);
        }
        return response;
    }
    private void save(TranslateResponse response,TranslateChannel translateChannel){
        executorService.execute(()->translateCacheService.saveCache(translateChannel,response));
        executorService.execute(()->{
            TransformInformation information = new TransformInformation();
            information.setTargetValue(response.getTarget());
            information.setWordLength(response.getWordLength());
            information.setCreator(translateChannel.channelName());
            information.setDelFlag(0);
            information.setSourceValue(translateChannel.getSource());
            information.setText(translateChannel.getText());
            information.setStatus("0");
            information.setType(translateChannel.channelType().name());
            translateMapper.insert(information);

        });
    }


    private Translator getTranslator(TranslateChannel translateChannel) {
        Object obj = ApplicationContextHolder.getBean(translateChannel.channelType().name());
        if(obj instanceof Translator){
            return (Translator) obj;
        }
        return null;
    }

    private TranslateResponse getTranslateInformation(TranslateChannel translateChannel) {
        TranslateResponse response = translateCacheService.getCache(translateChannel);
        if(response != null){
            return response;
        }
        response = translateMapper.selectBySource(translateChannel.getText());
        if(response!=null){
            translateCacheService.saveCache(translateChannel,response);
        }
        return response;
    }

}
