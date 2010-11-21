package org.codegist.common.reflect;

import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * ProxyFactory backed-up with CGlib {@link net.sf.cglib.proxy.Proxy}. Need CGlib in the classpath (<a href="http://cglib.sourceforge.net/">http://cglib.sourceforge.net/</a>).
 *
 * @see net.sf.cglib.proxy.Proxy
 */
public class CglibProxyFactory implements ProxyFactory {

    @SuppressWarnings("unchecked")
    public <T> T createProxy(ClassLoader classLoader, final Object target, final InvocationHandler handler, Class<?>[] interfaces) {
        return (T) Proxy.newProxyInstance(classLoader, interfaces, new DelegatorHandler(target, handler));
    }

    private static class DelegatorHandler implements net.sf.cglib.proxy.InvocationHandler {

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

    public <T> T createProxy(ClassLoader classLoader, InvocationHandler handler, Class<?>[] interfaces) {
        return this.<T>createProxy(classLoader, null, handler, interfaces);
    }
}
