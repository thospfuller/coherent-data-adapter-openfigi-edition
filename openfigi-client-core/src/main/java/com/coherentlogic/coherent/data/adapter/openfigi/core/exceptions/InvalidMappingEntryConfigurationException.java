package com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Used to facilitate a fail-fast approach to misconfigured mapping entries.
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class InvalidMappingEntryConfigurationException extends NestedRuntimeException {

    private static final long serialVersionUID = -1863768497329624546L;

    public InvalidMappingEntryConfigurationException(String msg) {
        super(msg);
    }

    public InvalidMappingEntryConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
