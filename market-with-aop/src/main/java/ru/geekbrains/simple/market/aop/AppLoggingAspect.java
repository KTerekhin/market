package ru.geekbrains.simple.market.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.geekbrains.simple.market.controllers.AuthController;
import ru.geekbrains.simple.market.controllers.CartController;
import ru.geekbrains.simple.market.controllers.OrderController;
import ru.geekbrains.simple.market.controllers.ProductController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AppLoggingAspect {
    private Long orderControllerTime = 0L;
    private Long authControllerTime = 0L;
    private Long cartControllerTime = 0L;
    private Long productControllerTime = 0L;
    private Map<String, Integer> allMethodMap = new HashMap<>();

    @Before("execution(public * ru.geekbrains.simple.market.*.*.*(..))")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (allMethodMap.containsKey(methodSignature.toString())) {
            allMethodMap.put(methodSignature.toString(), allMethodMap.get(methodSignature.toString()) + 1);
        } else {
            allMethodMap.put(methodSignature.toString(), 1);
        }
    }

    @After("execution(public * ru.geekbrains.simple.market.controllers.CartController.clearCart())")
    public void findMaxRunningMethod() {
        /**
         * Упорядочивает количество вызовов всех методов по убыванию
         */
//        allMethodMap.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .forEach(System.out::println);

        /**
         * Выводит только наиболее вызываемый метод
         */
        int maxValueInMap = (Collections.max(allMethodMap.values()));
        for (Map.Entry<String, Integer> entry : allMethodMap.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                System.out.println("Наиболее вызываемый метод: " + entry.getKey()/* + " " + entry.getValue()*/);
            }
        }
    }

    @Around("execution(public * ru.geekbrains.simple.market.controllers.AuthController.*(..))")
    public Object authControllerMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        authControllerTime += duration;
        return out;
    }

    @Around("execution(public * ru.geekbrains.simple.market.controllers.CartController.*(..))")
    public Object cartControllerMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        cartControllerTime += duration;
        return out;
    }

    @Around("execution(public * ru.geekbrains.simple.market.controllers.OrderController.*(..))")
    public Object orderControllerMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        orderControllerTime += duration;
        return out;
    }

    @Around("execution(public * ru.geekbrains.simple.market.controllers.ProductController.*(..))")
    public Object productControllerMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        productControllerTime += duration;
        return out;
    }

    @After("execution(public * ru.geekbrains.simple.market.controllers.CartController.clearCart())")
    public void findMaxTime() {
        Map<String, Long> mapTimeMethod = new HashMap<>();
        mapTimeMethod.put(OrderController.class.getName(), orderControllerTime);
        mapTimeMethod.put(AuthController.class.getName(), authControllerTime);
        mapTimeMethod.put(CartController.class.getName(), cartControllerTime);
        mapTimeMethod.put(ProductController.class.getName(), productControllerTime);

        Long maxValueInMap=(Collections.max(mapTimeMethod.values()));
        for (Map.Entry<String, Long> entry : mapTimeMethod.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                System.out.println("Больше всего времени уходит на: " + entry.getKey());
            }
        }
    }
}
