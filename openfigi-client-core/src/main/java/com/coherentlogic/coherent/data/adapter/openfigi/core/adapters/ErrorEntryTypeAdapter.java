package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.model.core.exceptions.MethodNotSupportedException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ErrorEntryTypeAdapter extends TypeAdapter<ErrorEntry> {

    @Override
    public ErrorEntry read(JsonReader reader) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void write(JsonWriter writer, ErrorEntry errorEntry) throws IOException {
        throw new MethodNotSupportedException("The write method is not supported.");
    }
}
