package org.codegist.common.marshal;

import java.io.Writer;

/**
 * Generic interface for a unmarshaller
 */
public interface Unmarshaller {

    String unmarshall(Object object) throws MarshallException;

    void unmarshall(Object object, Writer writer) throws MarshallException;

}
