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

import org.codegist.common.collect.Maps;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Xml Marshaller and Unmarshaller based on jdk's {@link javax.xml.bind.JAXBContext}.
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 * @see javax.xml.bind.JAXBContext
 */
public class JaxbMarshaller implements Marshaller, Unmarshaller {

    public static final String MODEL_PACKAGE_PROP = JaxbMarshaller.class.getName() + "#model-package";
    public static final String MODEL_FACTORY_PROP = JaxbMarshaller.class.getName() + "#model-factory";
    public static final String USER_JAXB_CONTEXT_PROP = JaxbMarshaller.class.getName() + "#jaxb-context";

    private final JAXBContext jaxbContext;

    public JaxbMarshaller(String modelPackage) throws MarshallException {
        try {
            this.jaxbContext = JAXBContext.newInstance(modelPackage);
        } catch (JAXBException e) {
            throw new MarshallException(e);
        }
    }

    public JaxbMarshaller(Class<?> factory) throws MarshallException {
        try {
            this.jaxbContext = JAXBContext.newInstance(factory);
        } catch (JAXBException e) {
            throw new MarshallException(e);
        }
    }

    public JaxbMarshaller(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public JaxbMarshaller(Map<String,Object> config) {
        config = Maps.defaultsIfNull(config);
        try {
            if(config.containsKey(MODEL_FACTORY_PROP)) {
                this.jaxbContext = JAXBContext.newInstance((Class) config.get(MODEL_FACTORY_PROP));
            }else if(config.containsKey(MODEL_PACKAGE_PROP)) {
                this.jaxbContext = JAXBContext.newInstance((String) config.get(MODEL_PACKAGE_PROP));
            }else if(config.containsKey(USER_JAXB_CONTEXT_PROP)) {
                this.jaxbContext = (JAXBContext) config.get(USER_JAXB_CONTEXT_PROP);
            }else{
                throw new IllegalArgumentException("Illegal jaxb config");
            }
        } catch (JAXBException e) {
            throw new MarshallException(e);
        }
    }

    public String unmarshall(Object object) {
        StringWriter writer = new StringWriter();
        this.unmarshall(object, writer);
        return writer.toString();
    }


    public void unmarshall(Object object, Writer writer) {
        try {
            jaxbContext.createMarshaller().marshal(object, writer);
            writer.close();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")

    public <T> T marshall(String content) {
        return (T) this.marshall(new StringReader(content));
    }

    @SuppressWarnings("unchecked")

    public <T> T marshall(InputStream is) {
        return (T) this.marshall(new InputStreamReader(is));
    }

    @SuppressWarnings("unchecked")

    public <T> T marshall(Reader reader) {
        try {
            return (T) jaxbContext.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    public <T> T marshall(String content, Type type) throws MarshallException {
        // Type parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(content);
    }


    public <T> T marshall(InputStream is, Type type) throws MarshallException {
        // Type parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(is);
    }


    public <T> T marshall(Reader reader, Type type) throws MarshallException {
        // Type parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(reader);
    }


    public <T> T marshall(String content, TypeReference<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(content);
    }


    public <T> T marshall(InputStream is, TypeReference<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(is);
    }


    public <T> T marshall(Reader reader, TypeReference<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(reader);
    }


    public <T> T marshall(String content, Class<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(content);
    }


    public <T> T marshall(InputStream is, Class<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(is);
    }


    public <T> T marshall(Reader reader, Class<T> type) {
        // TypeReference parameter ignored, using default jaxbcontext package to choose unmarshalled class type
        return this.<T>marshall(reader);
    }

}
