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

}
