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

public interface Logger {

    boolean isErrorOn();

    void error(Throwable e, String format, Object... args);

    void error(String format, Object... args);

    boolean isWarnOn();

    void warn(Throwable e, String format, Object... args);

    void warn(String format, Object... args);

    boolean isInfoOn();

    void info(Throwable e, String format, Object... args);

    void info(String format, Object... args);

    boolean isDebugOn();

    void debug(Throwable e, String format, Object... args);

    void debug(String format, Object... args);


    boolean isTraceOn();

    void trace(Throwable e, String format, Object... args);

    void trace(String format, Object... args);

}