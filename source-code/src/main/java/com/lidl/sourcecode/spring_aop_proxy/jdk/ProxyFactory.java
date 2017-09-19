package com.lidl.sourcecode.spring_aop_proxy.jdk;

import com.lidl.sourcecode.spring_aop_proxy.PerformanceUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdkProxy
 */
public class ProxyFactory implements InvocationHandler {

    private Object delegate;

    Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceUtil.startPerformance();
        Object result = null;
        try {
            result = method.invoke(delegate, args);
        } catch (Exception e) {
            // TODO: handle exceptions
        }
        PerformanceUtil.endPerformance();
        return result;
    }

}