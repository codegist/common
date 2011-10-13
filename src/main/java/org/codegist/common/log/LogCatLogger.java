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

import android.util.Log;

import java.io.Serializable;

import static java.lang.String.format;

/**
 * @author laurent.gilles@codegist.org
 */
public class LogCatLogger extends AbstractLogger implements Serializable {

    static final String LOGGER_TAG = LogCatLogger.class.getName() + ".TAG";
    private static final String TAG = System.getProperty(LOGGER_TAG, "CodeGist");
    private final String name;

    public LogCatLogger(String name) {
        this.name = name;
    }

    private String msg(Object o){
        return format("%s: %s", name , o);
    }

    @Override
    protected void logError(Object message, Throwable e) {
        Log.e(TAG, msg(message), e);
    }

    @Override
    protected void logError(Object message) {
        Log.e(TAG, msg(message));
    }

    @Override
    protected void logWarn(Object message, Throwable e) {
        Log.w(TAG, msg(message), e);
    }

    @Override
    protected void logWarn(Object message) {
        Log.w(TAG, msg(message));
    }

    @Override
    protected void logInfo(Object message, Throwable e) {
        Log.i(TAG, msg(message), e);
    }

    @Override
    protected void logInfo(Object message) {
        Log.i(TAG, msg(message));
    }

    @Override
    protected void logDebug(Object message, Throwable e) {
        Log.d(TAG, msg(message), e);
    }

    @Override
    protected void logDebug(Object message) {
        Log.d(TAG, msg(message));
    }

    @Override
    protected void logTrace(Object message, Throwable e) {
        Log.v(TAG, msg(message), e);
    }

    @Override
    protected void logTrace(Object message) {
        Log.v(TAG, msg(message));
    }

    @Override
    public boolean isErrorOn() {
        return Log.isLoggable(TAG, Log.ERROR);
    }

    @Override
    public boolean isWarnOn() {
        return Log.isLoggable(TAG, Log.WARN);
    }

    @Override
    public boolean isInfoOn() {
        return Log.isLoggable(TAG, Log.INFO);
    }

    @Override
    public boolean isDebugOn() {
        return Log.isLoggable(TAG, Log.DEBUG);
    }

    @Override
    public boolean isTraceOn() {
        return Log.isLoggable(TAG, Log.VERBOSE);
    }
}
