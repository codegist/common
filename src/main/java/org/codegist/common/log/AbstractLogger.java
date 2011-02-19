/*
 * Copyright 2010 CodeGist.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.common.log;

/**
 * Abstract logger implementation that formats logging requests using String.format and delegates the effective logging task to the implementor.
 * <p>nb: Formats and delegates only if the logging event level is enabled.
 * <p>nb bis: Catches and ignore any RuntimeException thrown by messy implementor.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public abstract class AbstractLogger extends Logger {

    private String format(String format, Object... args) {
        return args.length > 0 ? String.format(format, args) : format;
    }

    protected abstract void logError(Object message, Throwable e);

    protected abstract void logError(Object message);

    public void error(Throwable error) {
        try {
            if (!isErrorOn()) return;
            logError(error.getMessage(), error);
        } catch (RuntimeException ignore) {
        }
    }

    public void error(Throwable e, String format, Object... args) {
        try {
            if (!isErrorOn()) return;
            logError(format(format, args), e);
        } catch (RuntimeException ignore) {
        }
    }

    public void error(Throwable e, Object message) {
        try {
            if (!isErrorOn()) return;
            logError(message, e);
        } catch (RuntimeException ignore) {
        }
    }

    public void error(String format, Object... args) {
        try {
            if (!isErrorOn()) return;
            logError(format(format, args));
        } catch (RuntimeException ignore) {
        }
    }

    public void error(Object message) {
        try {
            if (!isErrorOn()) return;
            logError(message);
        } catch (RuntimeException ignore) {
        }
    }


    protected abstract void logWarn(Object message, Throwable e);

    protected abstract void logWarn(Object message);

    public void warn(Throwable e, String format, Object... args) {
        try {
            if (!isWarnOn()) return;
            logWarn(format(format, args), e);
        } catch (RuntimeException ignore) {
        }
    }

    public void warn(Throwable e, Object message) {
        try {
            if (!isWarnOn()) return;
            logWarn(message, e);
        } catch (RuntimeException ignore) {
        }
    }

    public void warn(String format, Object... args) {
        try {
            if (!isWarnOn()) return;
            logWarn(format(format, args));
        } catch (RuntimeException ignore) {
        }
    }

    public void warn(Object message) {
        try {
            if (!isWarnOn()) return;
            logWarn(message);
        } catch (RuntimeException ignore) {
        }
    }

    public void warn(Throwable error) {
        try {
            if (!isWarnOn()) return;
            logWarn(error.getMessage(), error);
        } catch (RuntimeException ignore) {
        }
    }


    protected abstract void logInfo(Object message, Throwable e);

    protected abstract void logInfo(Object message);

    public void info(Throwable e, String format, Object... args) {
        try {
            if (!isInfoOn()) return;
            logInfo(format(format, args), e);
        } catch (RuntimeException ignore) {
        }
    }

    public void info(Throwable e, Object message) {
        try {
            if (!isInfoOn()) return;
            logInfo(message, e);
        } catch (RuntimeException ignore) {
        }
    }

    public void info(String format, Object... args) {
        try {
            if (!isInfoOn()) return;
            logInfo(format(format, args));
        } catch (RuntimeException ignore) {
        }
    }

    public void info(Object message) {
        try {
            if (!isInfoOn()) return;
            logInfo(message);
        } catch (RuntimeException ignore) {
        }
    }

    public void info(Throwable error) {
        try {
            if (!isInfoOn()) return;
            logInfo(error.getMessage(), error);
        } catch (RuntimeException ignore) {
        }
    }


    protected abstract void logDebug(Object message, Throwable e);

    protected abstract void logDebug(Object message);

    public void debug(Throwable e, String format, Object... args) {
        try {
            if (!isDebugOn()) return;
            logDebug(format(format, args), e);
        } catch (RuntimeException ignore) {
        }
    }

    public void debug(Throwable e, Object message) {
        try {
            if (!isDebugOn()) return;
            logDebug(message, e);
        } catch (RuntimeException ignore) {
        }
    }

    public void debug(String format, Object... args) {
        try {
            if (!isDebugOn()) return;
            logDebug(format(format, args));
        } catch (RuntimeException ignore) {
        }
    }

    public void debug(Object message) {
        try {
            if (!isDebugOn()) return;
            logDebug(message);
        } catch (RuntimeException ignore) {
        }
    }

    public void debug(Throwable error) {
        try {
            if (!isDebugOn()) return;
            logDebug(error.getMessage(), error);
        } catch (RuntimeException ignore) {
        }
    }


    protected abstract void logTrace(Object message, Throwable e);

    protected abstract void logTrace(Object message);

    public void trace(Throwable e, String format, Object... args) {
        try {
            if (!isTraceOn()) return;
            logTrace(format(format, args), e);
        } catch (RuntimeException ignore) {
        }
    }

    public void trace(Throwable e, Object message) {
        try {
            if (!isTraceOn()) return;
            logTrace(message, e);
        } catch (RuntimeException ignore) {
        }
    }

    public void trace(String format, Object... args) {
        try {
            if (!isTraceOn()) return;
            logTrace(format(format, args));
        } catch (RuntimeException ignore) {
        }
    }

    public void trace(Object message) {
        try {
            if (!isTraceOn()) return;
            logTrace(message);
        } catch (RuntimeException ignore) {
        }
    }

    public void trace(Throwable error) {
        try {
            if (!isTraceOn()) return;
            logTrace(error.getMessage(), error);
        } catch (RuntimeException ignore) {
        }
    }


}
