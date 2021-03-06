/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.reflect;

import java.lang.reflect.Method;


/**
 * Abstract invocation handler that catches toString/equals/hashcode objects methods and executed them. Otherwhise delegate execution to doInvoke method.
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public abstract class ObjectMethodsAwareInvocationHandler implements InvocationHandler {

    protected abstract Object doInvoke(Object proxy, Method method, Object[] args) throws Throwable;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Methods.isToString(method)) {
            return this.toString();
        } else if (Methods.isEquals(method)) {
            return proxy == args[0];
        } else if (Methods.isHashCode(method)) {
            return this.hashCode();
        } else {
            return doInvoke(proxy, method, args);
        }
    }
}
