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

public class JdkLogger implements Logger, Serializable {

    private final transient java.util.logging.Logger logger;

    public JdkLogger(String name) {
        this.logger = java.util.logging.Logger.getLogger(name);
    }

    private String format(String format, Object... args){
        return args.length > 0 ? String.format(format, args) : format;
    }

    public boolean isErrorOn() {
        return logger.isLoggable(Level.SEVERE);
    }

    public void error(Throwable e, String format, Object... args) {
        logger.log(Level.SEVERE, format(format, args), e);
    }

    public void error(String format, Object... args) {
        logger.log(Level.SEVERE, format(format, args));
    }

    public boolean isWarnOn() {
        return logger.isLoggable(Level.WARNING);
    }

    public void warn(Throwable e, String format, Object... args) {
        logger.log(Level.WARNING, format(format, args), e);
    }

    public void warn(String format, Object... args) {
        logger.log(Level.WARNING, format(format, args));
    }

    public boolean isInfoOn() {
        return logger.isLoggable(Level.INFO);
    }

    public void info(Throwable e, String format, Object... args) {
        logger.log(Level.INFO, format(format, args), e);
    }

    public void info(String format, Object... args) {
        logger.log(Level.INFO, format(format, args));
    }

    public boolean isDebugOn() {
        return logger.isLoggable(Level.FINE);
    }

    public void debug(Throwable e, String format, Object... args) {
        logger.log(Level.FINE, format(format, args), e);
    }

    public void debug(String format, Object... args) {
        logger.log(Level.FINE, format(format, args));
    }

    public boolean isTraceOn() {
        return logger.isLoggable(Level.FINEST);
    }

    public void trace(Throwable e, String format, Object... args) {
        logger.log(Level.FINEST, format(format, args), e);
    }

    public void trace(String format, Object... args) {
        logger.log(Level.FINEST, format(format, args));
    }
}
