package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class Data {

    public static final String OPEN_FIGI_DATA = "openFIGIData";

    private final List<List<? extends SerializableBean>> entries;

    public Data() {
        this (new ArrayList<List<? extends SerializableBean>> ());
    }

    public Data(List<List<? extends SerializableBean>> dataEntries) {
        this.entries = dataEntries;
    }

    public List<List<? extends SerializableBean>> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Data [entries=" + entries + "]";
    }
}
