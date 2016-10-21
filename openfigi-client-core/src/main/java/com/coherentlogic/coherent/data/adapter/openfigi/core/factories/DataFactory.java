package com.coherentlogic.coherent.data.adapter.openfigi.core.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;

/**
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class DataFactory implements TypedFactory<Data> {

    public static final String BEAN_NAME = "dataFactory";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Data getInstance() {
        return applicationContext.getBean(Data.class);
    }
}
