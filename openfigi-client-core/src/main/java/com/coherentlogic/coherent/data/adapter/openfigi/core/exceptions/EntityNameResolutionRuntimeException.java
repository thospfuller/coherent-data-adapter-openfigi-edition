package com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions;

import org.springframework.core.NestedRuntimeException;

import com.coherentlogic.coherent.data.adapter.openfigi.core.hibernate.CGLIBEnhancedObjectTuplizer;

/**
 * An exception that is thrown when an object cannot not have it's name correctly resolved.
 *
 * @see {@link CGLIBEnhancedObjectTuplizer}
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class EntityNameResolutionRuntimeException extends NestedRuntimeException {

    private static final long serialVersionUID = 7758262896323978577L;

    public EntityNameResolutionRuntimeException(Object target) {
        super("Entity name resolution failed for the object: " + target);
    }
}
