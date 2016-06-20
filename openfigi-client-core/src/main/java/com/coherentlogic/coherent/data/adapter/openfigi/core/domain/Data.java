package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

public class Data {

    private final List<SerializableBean> entries;

    public Data() {
        this (new ArrayList<SerializableBean> ());
    }

    public Data(List<SerializableBean> dataEntries) {
        this.entries = dataEntries;
    }

    public List<SerializableBean> getEntries() {
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