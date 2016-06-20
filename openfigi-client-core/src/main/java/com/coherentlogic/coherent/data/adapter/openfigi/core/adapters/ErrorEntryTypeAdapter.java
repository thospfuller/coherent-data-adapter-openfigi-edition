package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.model.core.exceptions.MethodNotSupportedException;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ErrorEntryTypeAdapter extends TypeAdapter<ErrorEntry> {

    private static final Logger log = LoggerFactory.getLogger(ErrorEntryTypeAdapter.class);

    private final GsonBuilder gsonBuilder = new GsonBuilder();

    @Override
    public ErrorEntry read(JsonReader reader) throws IOException {

        ErrorEntry result = new ErrorEntry ();

        JsonObject errorEntryObject = gsonBuilder.create().fromJson(reader, JsonObject.class);

        JsonElement errorElement = errorEntryObject.get("error");

        if (!errorElement.isJsonNull()) {

            String error = errorElement.getAsString();

            log.info ("error: " + error);

            result.setError(error);
        }

        return result;
    }

    @Override
    public void write(JsonWriter writer, ErrorEntry errorEntry) throws IOException {
        throw new MethodNotSupportedException("The write method is not supported.");
    }
}
