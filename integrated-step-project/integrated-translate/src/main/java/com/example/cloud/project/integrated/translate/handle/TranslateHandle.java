package com.example.cloud.project.integrated.translate.handle;

import com.example.cloud.project.integrated.common.domain.LanguageType;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.translate.service.Translator;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @author gys
 * @date 2023/10/21
 * @description
 */
@Getter
public enum TranslateHandle {
    EN_2_CH(Translator::en2Ch, LanguageType.EN,LanguageType.CH),
    CH_2_EN(Translator::ch2En, LanguageType.CH,LanguageType.EN),
    ;

    private final BiFunction<Translator, RemoteTranslateRequest,TranslateResponse> function;
    private final LanguageType from;
    private final LanguageType to;

    TranslateHandle(BiFunction<Translator, RemoteTranslateRequest, TranslateResponse> function, LanguageType from, LanguageType to) {
        this.function = function;
        this.from = from;
        this.to = to;
    }

    public static Optional<TranslateHandle> handle(RemoteTranslateRequest request){
        LanguageType from = LanguageType.language(request.getFrom());
        LanguageType to = LanguageType.language(request.getTo());
        if(LanguageType.isAnyNone(from,to)){
            throw new RuntimeException("please check the value of language");
        }
        return Arrays.stream(values())
                .filter(translateHandle -> translateHandle.getFrom().equals(from) && translateHandle.getTo().equals(to))
                .findFirst();
    }
}
