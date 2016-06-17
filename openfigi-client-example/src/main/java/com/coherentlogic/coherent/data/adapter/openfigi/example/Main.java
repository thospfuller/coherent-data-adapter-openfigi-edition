package com.coherentlogic.coherent.data.adapter.openfigi.example;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;

public class Main {

    private final QueryBuilder queryBuilder = null;

    public Main() {

        Data data = queryBuilder
            .withApiKey("some key")
            .withContentTypeAsApplicationJson()
            .getRequestBody()
                .clear()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doPost(Data.class);

    }

    public static void main(String[] args) {
        
    }
}
