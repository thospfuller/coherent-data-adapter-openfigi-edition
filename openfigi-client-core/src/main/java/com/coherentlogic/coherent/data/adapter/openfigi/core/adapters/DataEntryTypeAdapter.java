package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.core.exceptions.MethodNotSupportedException;
import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * An adapter that converts JSON into an instance of {@link DataEntry}.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class DataEntryTypeAdapter extends AbstractEntryTypeAdapter<DataEntry> {

    private static final Logger log = LoggerFactory.getLogger(DataEntryTypeAdapter.class);

    public static final String BEAN_NAME = "dataEntryTypeAdapter",
        FIGI = "figi",
        SECURITY_TYPE = "securityType",
        MARKET_SECTOR = "marketSector",
        TICKER = "ticker",
        NAME = "name",
        UNIQUE_ID = "uniqueID",
        EXCH_CODE = "exchCode",
        SHARE_CLASS_FIGI = "shareClassFIGI",
        COMPOSIT_FIGI = "compositeFIGI",
        UNIQUE_ID_FUT_OPT = "uniqueIDFutOpt";

    private final GsonBuilder gsonBuilder;

    public DataEntryTypeAdapter(TypedFactory<DataEntry> dataEntryFactory) {
        this (new GsonBuilder(), dataEntryFactory);
    }

    public DataEntryTypeAdapter(GsonBuilder gsonBuilder, TypedFactory<DataEntry> dataEntryFactory) {
        super(dataEntryFactory);
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public DataEntry read(JsonReader reader) throws IOException {

        DataEntry result = getEntryFactory().getInstance();

        JsonObject dataEntryObject = gsonBuilder.create().fromJson(reader, JsonObject.class);

        JsonElement figi = dataEntryObject.get(FIGI);

        result.setFigi(getAsString (FIGI, figi));

        JsonElement securityType = dataEntryObject.get(SECURITY_TYPE);

        result.setSecurityType(getAsString (SECURITY_TYPE, securityType));

        JsonElement marketSector = dataEntryObject.get(MARKET_SECTOR);

        result.setMarketSector(getAsString (MARKET_SECTOR, marketSector));

        JsonElement ticker = dataEntryObject.get(TICKER);

        result.setTicker(getAsString (TICKER, ticker));

        JsonElement name = dataEntryObject.get(NAME);

        result.setName(getAsString (NAME, name));

        JsonElement uniqueID = dataEntryObject.get(UNIQUE_ID);

        result.setUniqueID(getAsString (UNIQUE_ID, uniqueID));

        JsonElement exchangeCode = dataEntryObject.get(EXCH_CODE);

        result.setExchangeCode(getAsString (EXCH_CODE, exchangeCode));

        JsonElement shareClassFIGI = dataEntryObject.get(SHARE_CLASS_FIGI);

        result.setShareClassFIGI(getAsString (SHARE_CLASS_FIGI, shareClassFIGI));

        JsonElement compositeFigi = dataEntryObject.get(COMPOSIT_FIGI);

        result.setCompositeFIGI(getAsString (COMPOSIT_FIGI, compositeFigi));

        JsonElement uniqueIDFutOptElement = dataEntryObject.get(UNIQUE_ID_FUT_OPT);

        result.setUniqueIDForFutureOption(getAsString (UNIQUE_ID_FUT_OPT, uniqueIDFutOptElement));

        return result;
    }

    String getAsString (String elementName, JsonElement jsonElement) {

        String result = null;

        if (!jsonElement.isJsonNull())
            result = jsonElement.getAsString();

        log.info(elementName + ": " + result);

        return result;
    }

    @Override
    public void write(JsonWriter writer, DataEntry dataEntry) throws IOException {
        throw new MethodNotSupportedException("The write method is not supported.");
    }
}
