package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.utils.ObjUtils;
import com.example.cloud.project.integrated.common.utils.StringTools;
import com.example.cloud.project.integrated.translate.dao.entity.TransformInformation;
import com.example.cloud.project.integrated.translate.dao.mapper.TransformInformationMapper;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.translate.handle.TranslateHandle;
import com.example.cloud.project.integrated.translate.holder.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;

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

    public TranslateResponse translate(RemoteTranslateRequest request) {
        // 如果自定义了完整的映射，直接使用完整的映射返回
        TranslateResponse response = getTranslateInformation(request);
        if (ObjUtils.isNotEmpty(response)) {
            return response;
        }
        String source = request.getSource();
        Translator translator = getTranslator(request);
        if (ObjUtils.isEmpty(translator)) {
            return null;
        }
        List<String> wordList = StringTools.split(source);
        String words = StringUtils.join(wordList, StringUtils.SPACE);
        request.setText(words);
        Optional<TranslateHandle> optional = TranslateHandle.handle(request);
        if(optional.isPresent()){
            BiFunction<Translator, RemoteTranslateRequest, TranslateResponse> function = optional.get().getFunction();
            response = function.apply(translator,request);
            if (response != null) {
                response.setTarget(response.getTarget().replace("的", ""));
                save(response,request);
            }
        }else {
            response = new TranslateResponse();
        }


        return response;
    }
    private void save(TranslateResponse response, RemoteTranslateRequest request){
        executorService.execute(()->translateCacheService.saveCache(request,response));
        executorService.execute(()->{
            TransformInformation information = new TransformInformation();
            information.setTargetValue(response.getTarget());
            information.setWordLength(response.getWordLength());
            information.setCreator(request.channelType().getOfficialName());
            information.setDelFlag(0);
            information.setSourceValue(request.getSource());
            information.setText(request.getText());
            information.setStatus("0");
            information.setType(request.channelType().name());
            translateMapper.insert(information);

        });
    }


    private Translator getTranslator(RemoteTranslateRequest request) {
        Object obj = ApplicationContextHolder.getBean(request.channelType().getCode());
        if(obj instanceof Translator){
            return (Translator) obj;
        }
        return null;
    }

    private TranslateResponse getTranslateInformation(RemoteTranslateRequest request) {
        TranslateResponse response = translateCacheService.getCache(request);
        if(response != null && response.getTarget()!=null){
            return response;
        }
        response = translateMapper.selectBySource(request.getSource());
        if(response != null){
            translateCacheService.saveCache(request,response);
        }
        return response;
    }

}
