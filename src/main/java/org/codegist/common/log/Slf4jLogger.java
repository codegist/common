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


public class Slf4jLogger implements Logger {
    private org.slf4j.Logger logger;

    public Slf4jLogger(String name) {
        this.logger = org.slf4j.LoggerFactory.getLogger(name);
    }

    private String format(String format, Object... args) {
        return args.length > 0 ? String.format(format, args) : format;
    }

    public boolean isErrorOn() {
        return logger.isErrorEnabled();
    }

    public void error(Throwable e, String format, Object... args) {
        logger.error(format(format, args), e);
    }

    public void error(String format, Object... args) {
        logger.error(format(format, args));
    }

    public boolean isWarnOn() {
        return logger.isWarnEnabled();
    }

    public void warn(Throwable e, String format, Object... args) {
        logger.warn(format(format, args), e);
    }

    public void warn(String format, Object... args) {
        logger.warn(format(format, args));
    }

    public boolean isInfoOn() {
        return logger.isInfoEnabled();
    }

    public void info(Throwable e, String format, Object... args) {
        logger.info(format(format, args), e);
    }

    public void info(String format, Object... args) {
        logger.info(format(format, args));
    }

    public boolean isDebugOn() {
        return logger.isDebugEnabled();
    }

    public void debug(Throwable e, String format, Object... args) {
        logger.debug(format(format, args), e);
    }

    public void debug(String format, Object... args) {
        logger.debug(format(format, args));
    }

    public boolean isTraceOn() {
        return logger.isTraceEnabled();
    }

    public void trace(Throwable e, String format, Object... args) {
        logger.trace(format(format, args), e);
    }

    public void trace(String format, Object... args) {
        logger.trace(format(format, args));
    }
}