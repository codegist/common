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

import org.codegist.common.reflect.Classes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
final class LoggerProvider {

    static final String LOGGER_CLASS_PROP = "org.codegist.common.log.class";

    private static final LoggerFactory DEFAULT_FACTORY = new LoggerFactory(NoOpLogger.class);
    private static final Map<String, LoggerFactory> LOGGER_FACTORIES =
            new LinkedHashMap<String, LoggerFactory>() {{
                put("org.apache.log4j.Logger", new LoggerFactory(Log4jLogger.class));
                put("org.slf4j.LoggerFactory", new LoggerFactory(Slf4jLogger.class));
                put("org.apache.commons.logging.LogFactory", new LoggerFactory(ApacheCommonsLogger.class));
            }};

    private LoggerProvider() {
    }

    static LoggerFactory getAvailableLoggerFactory() {
        ClassLoader cloader = ClassLoader.getSystemClassLoader();
        return getAvailableLoggerFactory(cloader);
    }

    static LoggerFactory getAvailableLoggerFactory(ClassLoader loader) {
        if (System.getProperty(LOGGER_CLASS_PROP) != null) {
            try {
                return new LoggerFactory((Class<? extends Logger>) Class.forName(System.getProperty(LOGGER_CLASS_PROP)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            for (Map.Entry<String, LoggerFactory> entry : LOGGER_FACTORIES.entrySet()) {
                if (Classes.isClassKnown(entry.getKey(), loader)) {
                    return entry.getValue();
                }
            }
            return DEFAULT_FACTORY;
        }
    }
}
