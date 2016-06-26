package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

@Entity
@Table(name=Data.OPEN_FIGI_DATA)
public class Data {

    public static final String OPEN_FIGI_DATA = "openFigiData";

    private final List<List<? extends SerializableBean>> entries;

    public Data() {
        this (new ArrayList<List<? extends SerializableBean>> ());
    }

    public Data(List<List<? extends SerializableBean>> dataEntries) {
        this.entries = dataEntries;
    }

    @OneToMany(cascade=CascadeType.ALL)
    public List<List<? extends SerializableBean>> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Data [entries=" + entries + "]";
    }
}
