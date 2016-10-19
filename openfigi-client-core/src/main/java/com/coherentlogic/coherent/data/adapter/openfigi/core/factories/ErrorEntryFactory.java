package com.coherentlogic.coherent.data.adapter.openfigi.core.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;

/**
 *
 */
public class ErrorEntryFactory implements TypedFactory<ErrorEntry> {

    public static final String BEAN_NAME = "errorEntryFactory";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public ErrorEntry getInstance() {
        return applicationContext.getBean(ErrorEntry.class);
    }
}
