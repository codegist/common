package org.codegist.common.reflect;

import java.lang.reflect.Method;

public interface ProxyFactory {

    <T> T createProxy(ClassLoader classLoader, InvocationHandler handler, Class<?>[] interfaces);

    <T> T createProxy(ClassLoader classLoader, Object target, InvocationHandler handler, Class<?>[] interfaces);

    interface InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    }
}
