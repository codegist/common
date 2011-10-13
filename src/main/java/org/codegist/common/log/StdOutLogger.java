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

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

/**
 * PrintStream Logger implementation.
 * <p>All levels are enabled, to be used for debugging purposes.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class StdOutLogger extends AbstractLogger implements Serializable {

    private static final String FORMAT = "%tc [%5s] - %s: %s\n";
    private final String name;
    private final transient PrintWriter out;

    /**
     * Redirects output to System.out
     *
     * @param name Name of the logger
     */
    public StdOutLogger(String name) {
        this(new PrintWriter(System.out), name);
    }

    public StdOutLogger(PrintWriter out, String name) {
        this.out = out;
        this.name = name;
    }

    private void log(String lvl, Object message, Throwable error) {
        String msg = message != null ? message.toString().replaceAll("%", "%%") : null;
        out.format(String.format(FORMAT, new Date().getTime(), lvl, name, msg));
        if (error != null) error.printStackTrace(out);
        out.flush();
    }

    @Override
    protected void logError(Object message, Throwable e) {
        log("ERROR", message, e);
    }

    @Override
    protected void logError(Object message) {
        logError(message, null);
    }

    @Override
    protected void logWarn(Object message, Throwable e) {
        log("WARN", message, e);
    }

    @Override
    protected void logWarn(Object message) {
        logWarn(message, null);
    }

    @Override
    protected void logInfo(Object message, Throwable e) {
        log("INFO", message, e);
    }

    @Override
    protected void logInfo(Object message) {
        logInfo(message, null);
    }

    @Override
    protected void logDebug(Object message, Throwable e) {
        log("DEBUG", message, e);
    }

    @Override
    protected void logDebug(Object message) {
        logDebug(message, null);
    }

    @Override
    protected void logTrace(Object message, Throwable e) {
        log("TRACE", message, e);
    }

    @Override
    protected void logTrace(Object message) {
        logTrace(message, null);
    }

    public boolean isErrorOn() {
        return true;
    }

    public boolean isWarnOn() {
        return true;
    }

    public boolean isInfoOn() {
        return true;
    }

    public boolean isDebugOn() {
        return true;
    }

    public boolean isTraceOn() {
        return true;
    }
}
