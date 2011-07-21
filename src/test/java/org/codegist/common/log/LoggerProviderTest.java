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

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class LoggerProviderTest {


    @Test
    public void testLogCatFactory() throws ClassNotFoundException {
        ClassLoader cLoader = mock(ClassLoader.class);
        when(cLoader.loadClass(any(String.class))).thenAnswer(newClassLoaderAnswer("android.util.Log"));
        LoggerFactory factory = LoggerProvider.getAvailableLoggerFactory(cLoader);
        assertEquals(LogCatLogger.class, factory.getLogger("test").getClass());
    }
    @Test
    public void testLog4jFactory() throws ClassNotFoundException {
        ClassLoader cLoader = mock(ClassLoader.class);
        when(cLoader.loadClass(any(String.class))).thenAnswer(newClassLoaderAnswer("org.apache.log4j.Logger"));
        LoggerFactory factory = LoggerProvider.getAvailableLoggerFactory(cLoader);
        assertEquals(Log4jLogger.class, factory.getLogger("test").getClass());
    }
    @Test
    public void testSlf4jFactory() throws ClassNotFoundException {
        ClassLoader cLoader = mock(ClassLoader.class);
        when(cLoader.loadClass(any(String.class))).thenAnswer(newClassLoaderAnswer("org.slf4j.LoggerFactory"));
        LoggerFactory factory = LoggerProvider.getAvailableLoggerFactory(cLoader);
        assertEquals(Slf4jLogger.class, factory.getLogger("test").getClass());
    }
    @Test
    public void testDefaultLogger() throws ClassNotFoundException {
        ClassLoader cLoader = mock(ClassLoader.class);
        LoggerFactory factory = LoggerProvider.getAvailableLoggerFactory(cLoader);
        assertEquals(NoOpLogger.class, factory.getLogger("test").getClass());
    }

    @Test
    public void testLoggerTryOrder() throws ClassNotFoundException {
        final String[] expectedOrder = {
                "android.util.Log",
                "org.apache.log4j.Logger",
                "org.slf4j.LoggerFactory",
                "org.apache.commons.logging.LogFactory"
        };
        ClassLoader cLoader = mock(ClassLoader.class);
        final AtomicInteger i = new AtomicInteger();
        when(cLoader.loadClass(any(String.class))).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                assertEquals(expectedOrder[i.getAndIncrement()], invocationOnMock.getArguments()[0]);
                return null;
            }
        });
        LoggerProvider.getAvailableLoggerFactory(cLoader);
        assertEquals(expectedOrder.length, i.get());
    }


    private static Answer newClassLoaderAnswer(final String expected){
        return new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArguments()[0].equals(expected)) {
                    return Class.forName(expected);
                }else{
                    return null;
                }
            }
        };
    }
}
