package org.example.firstboot.controller;

import org.example.firstboot.core.result.GlobalResult;
import org.example.firstboot.service.AopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/aop")
public class AopController {

    @Resource
    AopService aopService;

    @GetMapping("/func-a")
    public GlobalResult funA() {

        aopService.funcA();
        return GlobalResult.success();
    }

    @GetMapping("/func-b")
    public GlobalResult funB() {

        return GlobalResult.success(aopService.funcB());
    }

    @GetMapping("/func-c")
    public GlobalResult funC() {

        return GlobalResult.success(aopService.funcC());
    }
}
