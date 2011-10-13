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

package org.codegist.common.log;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
class LoggerFactory {

    private final Map<String, Reference<Logger>> flyweight;
    private final Class<? extends Logger> loggerCls;

    public LoggerFactory(Map<String, Reference<Logger>> flyweight, Class<? extends Logger> loggerCls) {
        this.flyweight = flyweight;
        this.loggerCls = loggerCls;
    }

    /**
     * @param loggerCls Expected class must be a static innerclass of the implementor, with a public constructor with a String argument
     */
    public LoggerFactory(Class<? extends Logger> loggerCls) {
        this(new HashMap<String, Reference<Logger>>(), loggerCls);
    }

    public Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public Logger getLogger(String name) {
        Reference<Logger> loggerRef = flyweight.get(name);
        Logger logger = loggerRef != null ? loggerRef.get() : null;
        if (logger == null) {
            try {
                logger = loggerCls.getConstructor(String.class).newInstance(name);
            } catch (Exception retry) {
                try {
                    logger = loggerCls.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            flyweight.put(name, new SoftReference<Logger>(logger));
        }
        return logger;
    }
}
