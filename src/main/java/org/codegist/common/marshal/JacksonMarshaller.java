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
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Json Marshaller and Unmarshaller based on {@link org.codehaus.jackson.map.ObjectMapper}. Requires Jackson lib in the classpath (<a href="http://jackson.codehaus.org/">http://jackson.codehaus.org/</a>).
 *
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 * @see org.codehaus.jackson.map.ObjectMapper
 */
public class JacksonMarshaller implements Marshaller, Unmarshaller {

    public static final String USER_OBJECT_MAPPER_PROP = JacksonMarshaller.class.getName() + "#user-object-mapper";
    public static final String DESERIALIZATION_CONFIG_MAP_PROP = JacksonMarshaller.class.getName() + "#deserialization-config-map";

    private final ObjectMapper jackson;

    public JacksonMarshaller() {
        this(new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public JacksonMarshaller(ObjectMapper jackson) {
        this.jackson = jackson;
    }

    public JacksonMarshaller(Map<String, Object> config) {
        config = Maps.defaultsIfNull(config);
        ObjectMapper mapper = null;
        if(config.containsKey(USER_OBJECT_MAPPER_PROP)) {
            mapper = (ObjectMapper) config.get(USER_OBJECT_MAPPER_PROP);
        }else{
            mapper = new ObjectMapper();
        }
        if(config.containsKey(DESERIALIZATION_CONFIG_MAP_PROP)) {
            Map<String, Boolean> deserializationConfig = (Map<String, Boolean>) config.get(DESERIALIZATION_CONFIG_MAP_PROP);
            for (Map.Entry<String, Boolean> entry : deserializationConfig.entrySet()) {
                mapper = mapper.configure(DeserializationConfig.Feature.valueOf(entry.getKey()), entry.getValue());
            }
        }else{
            mapper = mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        this.jackson = mapper;
    }

    public String unmarshall(Object object) {
        Writer w = new StringWriter();
        unmarshall(object, w);
        return w.toString();
    }


    public void unmarshall(Object object, Writer writer) {
        try {
            jackson.writeValue(writer, object);
        } catch (IOException e) {
            throw new MarshallException(e);
        }

    }


    public Object marshall(String content) throws MarshallException {
        try {
            return jackson.readValue(content, Object.class);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public Object marshall(InputStream is) throws MarshallException {
        try {
            return jackson.readValue(is, Object.class);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public Object marshall(Reader reader) throws MarshallException {
        try {
            return jackson.readValue(reader, Object.class);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(String content, Type type) throws MarshallException {
        try {
            return jackson.<T>readValue(content, TypeFactory.type(type));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(InputStream is, Type type) throws MarshallException {
        try {
            return jackson.<T>readValue(is, TypeFactory.type(type));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(Reader reader, Type type) throws MarshallException {
        try {
            return jackson.<T>readValue(reader, TypeFactory.type(type));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(String content, Class<T> type) {
        try {
            return jackson.<T>readValue(content, type);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(InputStream is, Class<T> type) {
        try {
            return jackson.<T>readValue(is, type);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(Reader reader, Class<T> type) {
        try {
            return jackson.<T>readValue(reader, type);
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(Reader reader, TypeReference<T> type) {
        try {
            return jackson.<T>readValue(reader, TypeFactory.type(type.getType()));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(String content, TypeReference<T> type) {
        try {
            return jackson.<T>readValue(content, TypeFactory.type(type.getType()));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }


    public <T> T marshall(InputStream is, TypeReference<T> type) {
        try {
            return jackson.<T>readValue(is, TypeFactory.type(type.getType()));
        } catch (IOException e) {
            throw new MarshallException(e);
        }
    }
}
