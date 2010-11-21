package org.codegist.common.marshal;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Json Marshaller and Unmarshaller based on {@link org.codehaus.jackson.map.ObjectMapper}. Requires Jackson lib in the classpath (<a href="http://jackson.codehaus.org/">http://jackson.codehaus.org/</a>).
 *
 * @see org.codehaus.jackson.map.ObjectMapper
 */
public class JacksonMarshaller implements Marshaller, Unmarshaller {

    private final ObjectMapper jackson;

    public JacksonMarshaller() {
        this(new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public JacksonMarshaller(ObjectMapper jackson) {
        this(jackson, null);
    }

    public JacksonMarshaller(ObjectMapper jackson, Map<String, Boolean> deserializationConfig) {
        this.jackson = jackson;
        if (deserializationConfig != null) {
            for (Map.Entry<String, Boolean> entry : deserializationConfig.entrySet()) {
                jackson.configure(DeserializationConfig.Feature.valueOf(entry.getKey()), entry.getValue());
            }
        }
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
