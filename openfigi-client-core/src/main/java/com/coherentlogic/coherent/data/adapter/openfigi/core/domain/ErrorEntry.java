package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * 
 * @author Thomas P. Fuller
 */
public class ErrorEntry extends SerializableBean {

    private static final long serialVersionUID = 1743297826917452185L;

    private String error;

    public ErrorEntry() {
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorEntry [error=" + error + "]";
    }
}
