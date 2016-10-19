package com.coherentlogic.coherent.data.adapter.openfigi.core.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;

/**
 *
 */
public class DataEntryFactory implements TypedFactory<DataEntry> {

    public static final String BEAN_NAME = "dataEntryFactory";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public DataEntry getInstance() {
        return applicationContext.getBean(DataEntry.class);
    }
}
