package com.tpe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BeanController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/beans")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,String> getBeans(){
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Map<String,String> map = new HashMap<>();

        for (String beanName : beanNames){
            map.put(beanName,applicationContext.getBean(beanName).toString());
        }
        return map;
    }
}
