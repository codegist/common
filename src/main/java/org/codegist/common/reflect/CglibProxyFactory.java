/*
 * Copyright 2010 CodeGist.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.common.reflect;

import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * ProxyFactory backed-up with CGlib {@link net.sf.cglib.proxy.Proxy}. Need CGlib in the classpath (<a href="http://cglib.sourceforge.net/">http://cglib.sourceforge.net/</a>).
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
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
