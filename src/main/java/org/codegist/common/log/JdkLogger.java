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
import java.util.logging.Level;

/**
 * JDK Logger implementation
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class JdkLogger extends AbstractLogger implements Serializable {

    private final transient java.util.logging.Logger logger;

    public JdkLogger(String name) {
        this.logger = java.util.logging.Logger.getLogger(name);
    }

    @Override
    protected void logError(Object message, Throwable e) {
        logger.log(Level.SEVERE, String.valueOf(message), e);
    }

    @Override
    protected void logError(Object message) {
        logger.log(Level.SEVERE, String.valueOf(message));
    }

    @Override
    protected void logWarn(Object message, Throwable e) {
        logger.log(Level.WARNING, String.valueOf(message), e);
    }

    @Override
    protected void logWarn(Object message) {
        logger.log(Level.WARNING, String.valueOf(message));
    }

    @Override
    protected void logInfo(Object message, Throwable e) {
        logger.log(Level.INFO, String.valueOf(message), e);
    }

    @Override
    protected void logInfo(Object message) {
        logger.log(Level.INFO, String.valueOf(message));
    }

    @Override
    protected void logDebug(Object message, Throwable e) {
        logger.log(Level.FINE, String.valueOf(message), e);
    }

    @Override
    protected void logDebug(Object message) {
        logger.log(Level.FINE, String.valueOf(message));
    }

    @Override
    protected void logTrace(Object message, Throwable e) {
        logger.log(Level.FINEST, String.valueOf(message), e);
    }

    @Override
    protected void logTrace(Object message) {
        logger.log(Level.FINEST, String.valueOf(message));
    }

    public boolean isErrorOn() {
        return logger.isLoggable(Level.SEVERE);
    }

    public boolean isWarnOn() {
        return logger.isLoggable(Level.WARNING);
    }

    public boolean isInfoOn() {
        return logger.isLoggable(Level.INFO);
    }

    public boolean isDebugOn() {
        return logger.isLoggable(Level.FINE);
    }

    public boolean isTraceOn() {
        return logger.isLoggable(Level.FINEST);
    }
}
