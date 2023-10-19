package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.R;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.utils.ObjUtils;
import com.example.cloud.project.integrated.common.utils.StringTools;
import com.example.cloud.project.integrated.translate.dao.mapper.TransformInformationMapper;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.translate.holder.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public R<TranslateResponse> auto(TranslateChannel translateChannel) {
        return null;
    }

    /**
     * 英译中
     *
     * @param translateChannel 源
     * @return {@link String}
     */
    public TranslateResponse translateEn2Ch(TranslateChannel translateChannel) {
        return translate(translateChannel,"en2ch");
    }

    /**
     * 中译英
     *
     * @param translateChannel 源
     * @return {@link String}
     */
    public TranslateResponse translateCh2En(TranslateChannel translateChannel) {
        return translate(translateChannel,"ch2en");
    }

    public TranslateResponse translate(TranslateChannel translateChannel,String transType) {
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
        if("en2ch".equals(transType)){
            response = translator.en2Ch(translateChannel);
        }else if ("ch2en".equals(transType)){
            response = translator.ch2En(translateChannel);
        }else {
            return null;
        }
        if (response != null) {
            response.setTarget(response.getTarget().replace("的", ""));
        }
        translateCacheService.saveCache(translateChannel,response);
        return response;
    }

    private Translator getTranslator(TranslateChannel translateChannel) {
        Object obj = ApplicationContextHolder.getBean(translateChannel.channel().name());
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
