package com.example.cloud.project.integrated.translate.controller;

import com.example.cloud.project.integrated.common.domain.R;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.translate.service.TranslatorMangerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/18 16:13
 */
@RestController
@Slf4j
@RequestMapping("/translator/manager/v1")
public class TranslatorMangerController {

    @Autowired
    private TranslatorMangerService service;

    @PostMapping("/auto")
    public R<TranslateResponse> auto(TranslateChannel translateChannel){
        return service.auto(translateChannel);
    }

}
