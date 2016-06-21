package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.factories.TypedFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

public abstract class AbstractEntryTypeAdapter<T extends SerializableBean> extends TypeAdapter<T> {

    private final GsonBuilder gsonBuilder;

    private final TypedFactory<T> entryFactory;

    public AbstractEntryTypeAdapter(TypedFactory<T> entryFactory) {
        this (new GsonBuilder(), entryFactory);
    }

    public AbstractEntryTypeAdapter(GsonBuilder gsonBuilder, TypedFactory<T> dataEntryFactory) {
        this.gsonBuilder = gsonBuilder;
        this.entryFactory = dataEntryFactory;
    }

    public GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    public TypedFactory<T> getEntryFactory() {
        return entryFactory;
    }
}
