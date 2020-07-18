package com.fancyfrog.reddit.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    String build(String message){
        Context context = new Context();
        context.setVariable("message",message);
        return templateEngine.process("verification_mail",context);
    }
}
