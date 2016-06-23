package com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions;

import org.springframework.core.NestedRuntimeException;

public class ConstraintViolationException extends NestedRuntimeException {

    private static final long serialVersionUID = -3512695726616985259L;

    public ConstraintViolationException(String msg) {
        super(msg);
    }

    public ConstraintViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
