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

package org.codegist.common.log;

/**
 * Logger providers, log are automatically redirected to the classpath defined logging system.
 * <p>Currently supported one are: Log4j, Slf4j.
 * <p>If no supported logger is found in the classpath, it defaults to {@link org.codegist.common.log.NoOpLogger}.
 * <p>Setting {@value org.codegist.common.log.LoggerProvider#LOGGER_CLASS_PROP} system property to the fully qualified name of a class implementing {@link Logger} will default to the given logger implementation. 
 */
public final class Log {

    private static final LoggerFactory FACTORY = LoggerProvider.getAvailableLoggerFactory();

    public static Logger getLogger(Class<?> clazz) {
        return FACTORY.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return FACTORY.getLogger(name);
    }
}
