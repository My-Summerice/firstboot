package org.example.firstboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.firstboot.core.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义一个切入点（也就是一组连接点）
     * 这里指定的是service/impl目录下AopServiceImpl类的所有传参和返回值不限的方法
     */
    @Pointcut("execution(* org.example.firstboot.service.impl.AopServiceImpl.*(..))")
    private void cut() {

    }

    /**
     * 前置通知
     */
    @Before("cut()")
    public void before() {

        System.out.println("-----------------");
        System.out.println("前置通知");
    }

    /**
     * 环绕通知
     */
    @Around("cut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("-----------------");
        System.out.println("环绕通知：进入方法");

        // 获取目标对象的方法参数
        Object[] args = pjp.getArgs();

        // 获取目标对象的方法名
        String methodName = pjp.getSignature().getName();

        // 获取目标对象
        Object target = pjp.getTarget();

        // 获取代理对象
        Object proxy = pjp.getThis();

        // 执行目标方法，获取目标方法的返回值
        // 如果你想对这个返回值做一定处理后替换目标方法的返回值以至于对
        // 调用目标方法的方法产生影响
        // 那么你需要将该环绕通知的返回值类型修改为与目标方法返回值类型一致
        // 之后AOP框架就会进行这个返回值替换的操作
        Object result = pjp.proceed();

        System.out.println(Arrays.toString(args));
        System.out.println(methodName);
        System.out.println(target);
        System.out.println(proxy);
        System.out.println(result);

        System.out.println("环绕通知：退出方法");
    }

    /**
     * 后置通知
     */
    @After("cut()")
    public void after() {

        System.out.println("-----------------");
        System.out.println("后置通知");
    }

    /**
     * 后置通知（接收目标方法的返回值）
     */
    //    @AfterReturning("cut()") 这种入参的的情况不允许切面方法有入参
    //    该注解的参数有两个，一个指定切入点，一个指定该方法的入参名
    @AfterReturning(pointcut = "cut()", returning = "result")
    public void afterReturning(Object result) {

        System.out.println("后置通知，返回值为：" + result);
    }

    // todo: 这个与全局异常捕获是否会冲突？
    // todo: 测试结果，全局异常捕获会将异常通知覆盖掉，导致异常通知的结果为 null
    // 同样，该切面方法也有两种传参
    //    @AfterThrowing("cut()")
    @AfterThrowing(pointcut = "cut()", throwing = "e")
    public void afterThrowing(CustomException e) {

        System.out.println("异常通知，异常为：" + e.getMessage());
    }
}
