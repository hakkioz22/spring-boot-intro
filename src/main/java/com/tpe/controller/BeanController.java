package com.tpe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Current username: " + userDetails.getUsername() + " password: " +userDetails.getPassword() + userDetails.getAuthorities());

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Map<String,String> map = new HashMap<>();

        for (String beanName : beanNames){
            map.put(beanName,applicationContext.getBean(beanName).toString());
        }
        return map;
    }
}
