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


import java.io.Serializable;

/**
 * Slf4j Logger implementation
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class Slf4jLogger extends AbstractLogger implements Serializable {

    private final transient org.slf4j.Logger logger;

    public Slf4jLogger(String name) {
        this.logger = org.slf4j.LoggerFactory.getLogger(name);
    }

    @Override
    protected void logError(Object message, Throwable e) {
        this.logger.error(String.valueOf(message), e);
    }

    @Override
    protected void logError(Object message) {
        this.logger.error(String.valueOf(message));
    }

    @Override
    protected void logWarn(Object message, Throwable e) {
        this.logger.warn(String.valueOf(message), e);
    }

    @Override
    protected void logWarn(Object message) {
        this.logger.warn(String.valueOf(message));
    }

    @Override
    protected void logInfo(Object message, Throwable e) {
        this.logger.info(String.valueOf(message), e);
    }

    @Override
    protected void logInfo(Object message) {
        this.logger.info(String.valueOf(message));
    }

    @Override
    protected void logDebug(Object message, Throwable e) {
        this.logger.debug(String.valueOf(message), e);
    }

    @Override
    protected void logDebug(Object message) {
        this.logger.debug(String.valueOf(message));
    }

    @Override
    protected void logTrace(Object message, Throwable e) {
        this.logger.trace(String.valueOf(message), e);
    }

    @Override
    protected void logTrace(Object message) {
        this.logger.trace(String.valueOf(message));
    }

    public boolean isErrorOn() {
        return logger.isErrorEnabled();
    }

    public boolean isWarnOn() {
        return logger.isWarnEnabled();
    }

    public boolean isInfoOn() {
        return logger.isInfoEnabled();
    }

    public boolean isDebugOn() {
        return logger.isDebugEnabled();
    }

    public boolean isTraceOn() {
        return logger.isTraceEnabled();
    }
}
