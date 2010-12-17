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
 * Implementor can define a constructor taking a String argument that will be called with the requested logger name.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public abstract class Logger {

    private static final LoggerFactory FACTORY = LoggerProvider.getAvailableLoggerFactory();

    /**
     * Factory method returning Logger instances that redirects events to the user logging system. Detection is based on the current system classloader.
     * <p>Currently supported logging systems are: Log4j, Slf4j, Apache Commons Logging.
     * <p>For any other non supported logging system, it defaults to {@link org.codegist.common.log.NoOpLogger}.
     * <p>Setting "org.codegist.common.log.class" system property to the fully qualified name of a class implementing {@link Logger} will default to the given logger implementation.
     *
     * @param name logger name
     * @return logger instance
     */
    public static Logger getLogger(String name) {
        return FACTORY.getLogger(name);
    }

    /**
     * @see Logger#getLogger(String)
     * @param clazz Class the logger will be for
     * @return logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return FACTORY.getLogger(clazz);
    }

    public abstract boolean isErrorOn();
    public abstract void error(Throwable e, String format, Object... args);
    public abstract void error(Throwable e, Object message);
    public abstract void error(String format, Object... args);
    public abstract void error(Object message);

    public abstract boolean isWarnOn();
    public abstract void warn(Throwable e, String format, Object... args);
    public abstract void warn(Throwable e, Object message);
    public abstract void warn(String format, Object... args);
    public abstract void warn(Object message);

    public abstract boolean isInfoOn();
    public abstract void info(Throwable e, String format, Object... args);
    public abstract void info(Throwable e, Object message);
    public abstract void info(String format, Object... args);
    public abstract void info(Object message);

    public abstract boolean isDebugOn();
    public abstract void debug(Throwable e, String format, Object... args);
    public abstract void debug(Throwable e, Object message);
    public abstract void debug(String format, Object... args);
    public abstract void debug(Object message);

    public abstract boolean isTraceOn();
    public abstract void trace(Throwable e, String format, Object... args);
    public abstract void trace(Throwable e, Object message);
    public abstract void trace(String format, Object... args);
    public abstract void trace(Object message);

}
