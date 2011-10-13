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

import java.io.Serializable;

/**
 * Apache commons logging Logger implementation
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class ApacheCommonsLogger extends AbstractLogger implements Serializable {

    private final transient org.apache.commons.logging.Log logger;

    public ApacheCommonsLogger(String name) {
        this.logger = org.apache.commons.logging.LogFactory.getLog(name);
    }

    @Override
    protected void logError(Object message, Throwable e) {
        logger.error(message, e);
    }

    @Override
    protected void logError(Object message) {
        logger.error(message);
    }

    @Override
    protected void logWarn(Object message, Throwable e) {
        logger.warn(message, e);
    }

    @Override
    protected void logWarn(Object message) {
        logger.warn(message);
    }

    @Override
    protected void logInfo(Object message, Throwable e) {
        logger.info(message, e);
    }

    @Override
    protected void logInfo(Object message) {
        logger.info(message);
    }

    @Override
    protected void logDebug(Object message, Throwable e) {
        logger.debug(message, e);
    }

    @Override
    protected void logDebug(Object message) {
        logger.debug(message);
    }

    @Override
    protected void logTrace(Object message, Throwable e) {
        logger.trace(message, e);
    }

    @Override
    protected void logTrace(Object message) {
        logger.trace(message);
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
