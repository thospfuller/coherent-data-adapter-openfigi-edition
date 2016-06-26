package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * 
 * @author <a href="support@coherentlogic.com">Support</a>
 */
@Entity
@Table(name=ErrorEntry.OPEN_FIGI_ERROR_ENTRY)
public class ErrorEntry extends SerializableBean {

    private static final long serialVersionUID = 1743297826917452185L;

    public static final String OPEN_FIGI_ERROR_ENTRY = "openFigiErrorEntry";

    private String error;

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
