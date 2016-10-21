package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Tuplizer;

import com.coherentlogic.coherent.data.adapter.openfigi.core.hibernate.CGLIBEnhancedObjectTuplizer;
import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Entity
@Table(name=ErrorEntry.OPEN_FIGI_ERROR_ENTRY)
@Tuplizer(impl=CGLIBEnhancedObjectTuplizer.class)
public class ErrorEntry extends SerializableBean<ErrorEntry> {

    private static final long serialVersionUID = 1743297826917452185L;

    public static final String OPEN_FIGI_ERROR_ENTRY = "openFIGIErrorEntry";

    private String error;

    public static final String ERROR = "error";

    public void setError(@Changeable(ERROR) String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorEntry [error=" + error + ", toString()=" + super.toString() + "]";
    }
}
