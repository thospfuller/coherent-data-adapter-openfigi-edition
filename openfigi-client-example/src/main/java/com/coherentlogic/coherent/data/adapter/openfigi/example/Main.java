package com.coherentlogic.coherent.data.adapter.openfigi.example;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;

public class Main {

    public static final String OPEN_FIGI_API_KEY = "OPEN_FIGI_API_KEY";

    static final String API_KEY = System.getenv(OPEN_FIGI_API_KEY);

    private final QueryBuilder queryBuilder = null;

    public Main() {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .clear()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doGet(Data.class);
    }

    public static void main(String[] args) {
        
    }
}
