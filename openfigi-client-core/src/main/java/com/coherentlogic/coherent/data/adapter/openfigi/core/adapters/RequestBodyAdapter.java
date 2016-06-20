package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.model.core.adapters.InReturnAdapterSpecification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class RequestBodyAdapter implements InReturnAdapterSpecification<RequestBody, String> {

    private final GsonBuilder gsonBuilder;

    public RequestBodyAdapter () {
        this (new GsonBuilder ());
    }

    public RequestBodyAdapter (GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String adapt(RequestBody source) {

        Gson gson = gsonBuilder.create();

        String result = gson.toJson(source.getMappingEntries());

        return result;
    }
}
