package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import com.coherentlogic.coherent.data.adapter.core.adapters.InReturnAdapterSpecification;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * An adapter that converts an instance of {@link RequestBody} into JSON using the Google GSON API.
 *
 * @see <a href="https://github.com/google/gson">GSON</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
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
    public String adapt(RequestBody requestBody) {

        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        String result = gson.toJson(requestBody.getMappingEntries());

        return result;
    }
}
