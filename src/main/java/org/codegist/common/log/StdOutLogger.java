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

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

public class StdOutLogger implements Logger, Serializable {

    private static final String FORMAT = "%tc [%5s] - %s: %s\n";
    private final String name;
    private final transient PrintWriter out;

    public StdOutLogger(String name) {
        this(new PrintWriter(System.out), name);
    }
    public StdOutLogger(PrintWriter out, String name) {
        this.out = out;
        this.name = name;
    }

    private void log(String lvl, Throwable error, String format, Object... args) {
        String msg = (args.length == 0 ? format : String.format(format, args)).replaceAll("%","%%");
        out.format(String.format(FORMAT, new Date().getTime(), lvl, name, msg));
        if (error != null) {
            error.printStackTrace(out);
        }
        out.flush();
    }

    public boolean isErrorOn() {
        return true;
    }

    public void error(Throwable e, String format, Object... args) {
        log("ERROR", e, format, args);
    }

    public void error(String format, Object... args) {
        error(null, format, args);
    }

    public boolean isWarnOn() {
        return true;
    }

    public void warn(Throwable e, String format, Object... args) {
        log("WARN", e, format, args);
    }

    public void warn(String format, Object... args) {
        warn(null, format, args);
    }

    public boolean isInfoOn() {
        return true;
    }

    public void info(Throwable e, String format, Object... args) {
        log("INFO", e, format, args);
    }

    public void info(String format, Object... args) {
        info(null, format, args);
    }

    public boolean isDebugOn() {
        return true;
    }

    public void debug(Throwable e, String format, Object... args) {
        log("DEBUG", e, format, args);
    }

    public void debug(String format, Object... args) {
        debug(null, format, args);
    }

    public boolean isTraceOn() {
        return true;
    }

    public void trace(Throwable e, String format, Object... args) {
        log("TRACE", e, format, args);
    }

    public void trace(String format, Object... args) {
        trace(null, format, args);
    }
}
