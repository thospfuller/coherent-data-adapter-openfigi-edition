package com.coherentlogic.coherent.data.adapter.openfigi.core.extractors;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class DataExtractor implements ResponseExtractor<Data> {

    private static final Logger log = LoggerFactory.getLogger(DataExtractor.class);

    public static final String BEAN_NAME = "dataExtractor";

    private final GsonBuilder gsonBuilder;

    public DataExtractor (GsonBuilder gsonBuilder, TypeAdapter<Data> dataTypeAdapter) {
        this.gsonBuilder = gsonBuilder;
        gsonBuilder.registerTypeAdapter(Data.class, dataTypeAdapter);
    }

    @Override
    public Data extractData(ClientHttpResponse response) throws IOException {

        InputStream in = response.getBody();

        String json = IOUtils.toString(in);

        log.info("json: " + json);

        Data result = gsonBuilder.create().fromJson(json, Data.class);

        return result;
    }
}
