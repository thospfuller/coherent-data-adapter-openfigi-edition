package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

public class Data {

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
}
//"figi": "BBG000BLNNH6",
//"name": "INTL BUSINESS MACHINES CORP",
//"ticker": "IBM",
//"exchCode": "US",
//"compositeFIGI": "BBG000BLNNH6",
//"uniqueID": "EQ0010080100001000",
//"securityType": "Common Stock",
//"marketSector": "Equity",
//"shareClassFIGI": null,
//"uniqueIDFutOpt": null