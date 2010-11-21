package org.codegist.common.marshal;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Generic interface for a marshaller.
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
