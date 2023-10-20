package com.example.cloud.project.integrated.common.domain.channel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateResponse {
    private String target;
    private String wordLength;

    public static TranslateResponse of(String text) {
        return TranslateResponse.builder()
                .target(text)
                .wordLength(String.valueOf(text.length()))
                .build();
    }
    public static TranslateResponse of(String text,String wordLength) {
        return TranslateResponse.builder()
                .target(text)
                .wordLength(wordLength)
                .build();
    }

}
