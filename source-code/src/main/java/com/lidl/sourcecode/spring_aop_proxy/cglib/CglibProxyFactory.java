package com.lidl.sourcecode.spring_aop_proxy.cglib;

import com.lidl.sourcecode.spring_aop_proxy.PerformanceUtil;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibProxy
 */
public class CglibProxyFactory implements MethodInterceptor {

    private Object delegate;

    Object bind(Object delegate) {
        this.delegate = delegate;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(delegate.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        PerformanceUtil.startPerformance();
        Object o = proxy.invoke(this.delegate, args);
        PerformanceUtil.endPerformance();
        return o;
    }

}