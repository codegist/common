package org.codegist.common.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyFactory backed-up with Jdk native {@link java.lang.reflect.Proxy}
 *
 * @see java.lang.reflect.Proxy
 */
public class JdkProxyFactory implements ProxyFactory {

    @SuppressWarnings("unchecked")
    public <T> T createProxy(ClassLoader classLoader, final Object target, final InvocationHandler handler, Class<?>[] interfaces) {
        return (T) Proxy.newProxyInstance(classLoader, interfaces, new DelegatorHandler(target, handler));
    }

    private static class DelegatorHandler implements java.lang.reflect.InvocationHandler {

        private final Object target;
        private final InvocationHandler delegate;

        private DelegatorHandler(Object target, InvocationHandler delegate) {
            this.target = target;
            this.delegate = delegate;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return delegate.invoke(target != null ? target : proxy, method, args);
        }
    }

    public <T> T createProxy(ClassLoader classLoader, final InvocationHandler handler, Class<?>[] interfaces) {
        return this.<T>createProxy(classLoader, null, handler, interfaces);
    }
}
