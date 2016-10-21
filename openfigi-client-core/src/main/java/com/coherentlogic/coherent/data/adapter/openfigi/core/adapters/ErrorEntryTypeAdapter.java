package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.adapter.core.exceptions.MethodNotSupportedException;
import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ErrorEntryTypeAdapter extends AbstractEntryTypeAdapter<ErrorEntry> {

    public static final String BEAN_NAME = "errorEntryTypeAdapter", ERROR = "error";

    private static final Logger log = LoggerFactory.getLogger(ErrorEntryTypeAdapter.class);

    public ErrorEntryTypeAdapter(TypedFactory<ErrorEntry> entryFactory) {
        super(entryFactory);
    }

    @Override
    public ErrorEntry read(JsonReader reader) throws IOException {

        ErrorEntry result = getEntryFactory().getInstance();

        JsonObject errorEntryObject = getGsonBuilder().create().fromJson(reader, JsonObject.class);

        JsonElement errorElement = errorEntryObject.get(ERROR);

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
