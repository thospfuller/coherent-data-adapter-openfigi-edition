package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        log.info("read: method begins; reader: " + reader);

        Data result = new Data ();

        Gson gson = gsonBuilder.create();

        reader.beginArray ();

        while (reader.hasNext()) {

            JsonObject resultantObject = gsonBuilder.create().fromJson(reader, JsonObject.class);

            log.info("resultantObject: " + resultantObject); // both data and errors

            JsonElement dataElement = resultantObject.get("data");

            List<DataEntry> dataEntries = addData (gson, dataElement);

            result.getEntries().addAll(dataEntries);

            JsonElement errorElement = resultantObject.get("error");

            List<ErrorEntry> errorEntries = addErrors(gson, errorElement);

            result.getEntries().addAll(errorEntries);
        }

        reader.endArray ();

        return result;
    }

    /**
     * @todo Combine the addData and addErrors methods.
     */
    List<DataEntry> addData (Gson gson, JsonElement dataElement) {

        List<DataEntry> result = new ArrayList<DataEntry> ();

        if (dataElement != null && !dataElement.isJsonNull()) {

            JsonArray dataArray = dataElement.getAsJsonArray();

            log.info("dataArray: " + dataArray);

            Iterator<JsonElement> iterator = dataArray.iterator();

            while (iterator.hasNext()) {

                JsonElement nextElement = iterator.next();

                JsonObject nextObject = nextElement.getAsJsonObject(); // data

                DataEntry nextEntry = gson.fromJson(nextObject, DataEntry.class);

                result.add(nextEntry);

                log.info("nextEntry: " + nextEntry);
            }
        }
        return result;
    }

    /**
     * @todo Combine the addData and addErrors methods.
     */
    List<ErrorEntry> addErrors (Gson gson, JsonElement errorElement) {

        List<ErrorEntry> result = new ArrayList<ErrorEntry> ();

        if (errorElement !=null && !errorElement.isJsonNull()) {

            if (errorElement.isJsonArray()) {

                JsonArray errorArray = errorElement.getAsJsonArray();

                Iterator<JsonElement> iterator = errorArray.iterator();

                while (iterator.hasNext()) {

                    JsonElement nextElement = iterator.next();

                    JsonObject nextObject = nextElement.getAsJsonObject(); // error

                    ErrorEntry nextEntry = gson.fromJson(nextObject, ErrorEntry.class);

                    result.add(nextEntry);

                    log.info("nextEntry: " + nextEntry);
                }
            } else {

                String errorText = errorElement.getAsString();

                log.info("errorText: " + errorText);

                ErrorEntry nextEntry = new ErrorEntry ();

                nextEntry.setError(errorText);

                result.add(nextEntry);
            }
        }
        return result;
    }

    @Override
    public void write(JsonWriter arg0, Data arg1) throws IOException {
        throw new MethodNotSupportedException ("The write method is not supported.");
    }
}
