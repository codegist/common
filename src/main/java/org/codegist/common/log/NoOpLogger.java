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
 * Logger implementation that discards all logging events.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class NoOpLogger extends Logger implements Serializable {
    public boolean isErrorOn() {
        return false;
    }

    @Override
    public void error(Throwable error) {

    }

    @Override
    public void warn(Throwable error) {

    }

    @Override
    public void info(Throwable error) {

    }

    @Override
    public void debug(Throwable error) {

    }

    @Override
    public void trace(Throwable error) {

    }

    public void error(Throwable e, String format, Object... args) {

    }

    public void error(Throwable e, Object message) {

    }

    public void error(String format, Object... args) {

    }

    public void error(Object message) {

    }

    public boolean isWarnOn() {
        return false;
    }

    public void warn(Throwable e, String format, Object... args) {

    }

    public void warn(Throwable e, Object message) {

    }

    public void warn(String format, Object... args) {

    }

    public void warn(Object message) {

    }

    public boolean isInfoOn() {
        return false;
    }

    public void info(Throwable e, String format, Object... args) {

    }

    public void info(Throwable e, Object message) {

    }

    public void info(String format, Object... args) {

    }

    public void info(Object message) {

    }

    public boolean isDebugOn() {
        return false;
    }

    public void debug(Throwable e, String format, Object... args) {

    }

    public void debug(Throwable e, Object message) {

    }

    public void debug(String format, Object... args) {

    }

    public void debug(Object message) {

    }

    public boolean isTraceOn() {
        return false;
    }

    public void trace(Throwable e, String format, Object... args) {

    }

    public void trace(Throwable e, Object message) {

    }

    public void trace(String format, Object... args) {

    }

    public void trace(Object message) {

    }
}
