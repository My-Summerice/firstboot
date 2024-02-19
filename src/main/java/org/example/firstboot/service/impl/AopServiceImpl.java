package org.example.firstboot.service.impl;

import org.example.firstboot.core.exception.CustomException;
import org.example.firstboot.service.AopService;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {

    @Override
    public void funcA() {

        System.out.println("this is funcA");
    }

    @Override
    public String funcB() {

        System.out.println("this is funcB");
        return null;
    }

    @Override
    public String funcC() {

        System.out.println("this is funcC");
        throw new CustomException("aop自定义异常");
        //        try {
        //            throw new Exception("这是一个异常");
        //        } catch (Exception e) {
        //        }

        // Exception 必须在方法名后显示的声明异常或者在代码中使用 try-catch
        //        throw new Exception("这是一个异常");
    }
}
