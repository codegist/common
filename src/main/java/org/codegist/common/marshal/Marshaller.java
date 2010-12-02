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

package org.codegist.common.marshal;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Generic interface for a marshaller.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public interface Marshaller {

    <T> T marshall(String content) throws MarshallException;

    <T> T marshall(InputStream is) throws MarshallException;

    <T> T marshall(Reader reader) throws MarshallException;


    <T> T marshall(String content, TypeReference<T> type) throws MarshallException;

    <T> T marshall(InputStream is, TypeReference<T> type) throws MarshallException;

    <T> T marshall(Reader reader, TypeReference<T> type) throws MarshallException;


    <T> T marshall(String content, Type type) throws MarshallException;

    <T> T marshall(InputStream is, Type type) throws MarshallException;

    <T> T marshall(Reader reader, Type type) throws MarshallException;


    <T> T marshall(String content, Class<T> type) throws MarshallException;

    <T> T marshall(InputStream is, Class<T> type) throws MarshallException;

    <T> T marshall(Reader reader, Class<T> type) throws MarshallException;

}
