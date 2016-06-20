package com.coherentlogic.coherent.data.adapter.openfigi.core.extractors;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.DataTypeAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class DataExtractor implements ResponseExtractor {

    private static final Logger log = LoggerFactory.getLogger(DataExtractor.class);

    private final GsonBuilder gsonBuilder;

    public DataExtractor () {
        this (new GsonBuilder ());
    }

    public DataExtractor (GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
        gsonBuilder.registerTypeAdapter(Data.class, new DataTypeAdapter ());
    }

    @Override
    public Object extractData(ClientHttpResponse response) throws IOException {

        InputStream in = response.getBody();

        String json = IOUtils.toString(in);

        log.info("json: " + json);

//        JsonElement jsonElement = gsonBuilder.create().fromJson(json, JsonElement.class);
//
//        JsonArray jsonArray = jsonElement.getAsJsonArray();
//
//        JsonElement dataElement = jsonArray.get(0);
//
//        log.info("dataElement: " + dataElement);

        Data result = gsonBuilder.create().fromJson(json, Data.class);

//        Map map = (Map) list.get(0);
//
//        Object object = map.get("data");
//
//        log.info("map: " + object);

//        Data data = gsonBuilder.create().fromJson((Map) list.get(0), Data.class);
//
//        log.info("data: " + data);

        return result;
    }
}
