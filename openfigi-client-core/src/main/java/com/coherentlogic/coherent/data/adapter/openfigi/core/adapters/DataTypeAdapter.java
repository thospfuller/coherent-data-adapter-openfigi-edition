package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.exceptions.MethodNotSupportedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DataTypeAdapter extends TypeAdapter<Data> {

    private static final Logger log = LoggerFactory.getLogger(DataTypeAdapter.class);

    private final GsonBuilder gsonBuilder;

    public DataTypeAdapter() {
        gsonBuilder = new GsonBuilder ();
        gsonBuilder.registerTypeAdapter(DataEntry.class, new DataEntryTypeAdapter ());
        gsonBuilder.registerTypeAdapter(ErrorEntry.class, new ErrorEntryTypeAdapter ());
    }

    @Override
    public Data read(JsonReader reader) throws IOException {

        log.info("DATA TYPE ADAPTER!!!");

        Data result = new Data ();

        Gson gson = gsonBuilder.create();

        reader.beginArray ();

        JsonObject resultantObject = gsonBuilder.create().fromJson(reader, JsonObject.class);

        log.info("resultantObject: " + resultantObject); // both data and errors

        JsonElement dataElement = resultantObject.get("data");

        JsonArray dataArray = dataElement.getAsJsonArray();

        log.info("dataArray: " + dataArray);

        Iterator<JsonElement> iterator = dataArray.iterator();

        while (iterator.hasNext()) {

            JsonElement nextElement = iterator.next();

            JsonObject nextObject = nextElement.getAsJsonObject(); // data

            SerializableBean nextEntry = gson.fromJson(nextObject, DataEntry.class);

            result.getEntries().add(nextEntry);

            log.info("nextEntry: " + nextEntry);
        }

        reader.endArray ();

        return result;
    }

    @Override
    public void write(JsonWriter arg0, Data arg1) throws IOException {
        throw new MethodNotSupportedException ("The write method is not supported.");
    }
}
//Map map = (Map) list.get(0);
//
//Object object = map.get("data");
//
//log.info(">>>>>>>>>>>>>>> map: " + object.getClass());

//Data data = gson.fromJson(, DataEntry.class);
//
//log.info("data: " + data);

//reader.endArray();
