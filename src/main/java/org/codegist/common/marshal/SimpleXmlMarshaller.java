/*
 * Copyright 2010 CodeGist.org
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
 *  ==================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.marshal;

import org.codegist.common.collect.Maps;
import org.codegist.common.reflect.Types;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laurent.gilles@codegist.org
 */
public class SimpleXmlMarshaller implements Marshaller, Unmarshaller {

    public static final String STRICT_PROP = SimpleXmlMarshaller.class.getName() + "#strict";
    public static final String USER_SERIALIZER_PROP = SimpleXmlMarshaller.class.getName() + "#user-serializer";
    public static final String DATE_FORMAT_PROP = SimpleXmlMarshaller.class.getName() + "#date.format";
    public static final String BOOLEAN_FORMAT_PROP = SimpleXmlMarshaller.class.getName() + "#boolean.format";

    public static final boolean DEFAULT_STRICT = true;
    private final boolean strict;
    private final Serializer serializer;

    public SimpleXmlMarshaller() {
        this(new Persister(), DEFAULT_STRICT);
    }
    public SimpleXmlMarshaller(Serializer serializer, boolean strict) {
        this.serializer = serializer;
        this.strict = strict;
    }

    public SimpleXmlMarshaller(Map<String,Object> config) {
        config = Maps.defaultsIfNull(config);
        if(config.containsKey(STRICT_PROP)) {
            this.strict = (Boolean) config.get(STRICT_PROP);
        }else{
            this.strict = DEFAULT_STRICT;
        }
        if(config.containsKey(USER_SERIALIZER_PROP)) {
            this.serializer = (Serializer) config.get(USER_SERIALIZER_PROP);
        }else{
            MatcherComposite.Builder matcherComposite = new MatcherComposite.Builder();
            if(config.containsKey(DATE_FORMAT_PROP)) {
                matcherComposite.bind(Date.class, new DateMatcher((String) config.get(DATE_FORMAT_PROP)));
            }
            if(config.containsKey(BOOLEAN_FORMAT_PROP)) {
                String trueVal = ((String)config.get(BOOLEAN_FORMAT_PROP)).split(":")[0];
                String falseVal = ((String)config.get(BOOLEAN_FORMAT_PROP)).split(":")[1];
                matcherComposite.bind(Boolean.class, new BooleanMatcher(trueVal, falseVal));
            }
            if(matcherComposite.hasTransformers()) {
                this.serializer = new Persister(matcherComposite.build());
            }else{
                this.serializer = new Persister();
            }

        }
    }

    public String unmarshall(Object object) throws MarshallException {
        StringWriter sw = new StringWriter();
        unmarshall(object, sw);
        return sw.toString();
    }

    public void unmarshall(Object object, Writer writer) throws MarshallException {
        try {
            serializer.write(object, writer);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public Object marshall(String content) throws MarshallException {
        try {
            return serializer.read(Object.class, content, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public Object marshall(InputStream is) throws MarshallException {
        try {
            return serializer.read(Object.class, is, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public Object marshall(Reader reader) throws MarshallException {
        try {
            return serializer.read(Object.class, reader, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(String content, TypeReference<T> type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type.getType()), content, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(InputStream is, TypeReference<T> type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type.getType()), is, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(Reader reader, TypeReference<T> type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type.getType()), reader, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(String content, Type type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type), content, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(InputStream is, Type type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type), is, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(Reader reader, Type type) throws MarshallException {
        try {
            return serializer.<T>read((Class<T>) Types.getClass(type), reader, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(String content, Class<T> type) throws MarshallException {
        try {
            return serializer.<T>read(type, content, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(InputStream is, Class<T> type) throws MarshallException {
        try {
            return serializer.<T>read(type, is, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }

    public <T> T marshall(Reader reader, Class<T> type) throws MarshallException {
        try {
            return serializer.<T>read(type, reader, strict);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
    }


    public static class MatcherComposite implements Matcher {
        private final Map<Class, Transform> transformerMap;

        private MatcherComposite(Map<Class, Transform> transformerMap) {
            this.transformerMap = transformerMap;
        }

        private static class Builder {
            private final Map<Class, Transform> transformerMap = new HashMap<Class, Transform>();

            public <T> Builder bind(Class<T> clazz, Transform<T> transform) {
                transformerMap.put(clazz, transform);
                return this;
            }

            public boolean hasTransformers(){
                return !transformerMap.isEmpty();
            }

            public MatcherComposite build() {
                return new MatcherComposite(transformerMap);
            }
        }

        public Transform match(Class type) throws Exception {
            return transformerMap.get(type);
        }
    }

    public static class BooleanMatcher implements Transform<Boolean> {
        private final String trueVal;
        private final String falseVal;

        private BooleanMatcher(String trueVal, String falseVal) {
            this.trueVal = trueVal;
            this.falseVal = falseVal;
        }

        public Boolean read(String value) throws Exception {
            return !falseVal.equals(value);
        }

        public String write(Boolean value) throws Exception {
            return Boolean.TRUE.equals(value) ? trueVal : falseVal;
        }
    }

    public static class DateMatcher implements Transform<Date> {
        private final DateFormat DF;

        private DateMatcher(String format) {
            this.DF = new SimpleDateFormat(format);
        }

        public synchronized Date read(String value) throws Exception {
            return DF.parse(value);
        }

        public synchronized String write(Date value) throws Exception {
            return DF.format(value);
        }
    }
}


