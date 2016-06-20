package com.coherentlogic.coherent.data.adapter.openfigi.core.adapters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.model.core.exceptions.MethodNotSupportedException;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DataEntryTypeAdapter extends TypeAdapter<DataEntry> {

    private static final Logger log = LoggerFactory.getLogger(DataEntryTypeAdapter.class);

    private final GsonBuilder gsonBuilder = new GsonBuilder();
    
    @Override
    public DataEntry read(JsonReader reader) throws IOException {

        DataEntry result = new DataEntry ();

        JsonObject dataEntryObject = gsonBuilder.create().fromJson(reader, JsonObject.class);

        String figi = dataEntryObject.get("figi").getAsString();

        log.info("figi: " + figi);

        result.setFigi(figi);

        String securityType = dataEntryObject.get("securityType").getAsString();

        log.info("securityType: " + securityType);

        result.setSecurityType(securityType);

        String marketSector = dataEntryObject.get("marketSector").getAsString();

        log.info("marketSector: " + marketSector);

        result.setMarketSector(marketSector);

        String ticker = dataEntryObject.get("ticker").getAsString();

        log.info("ticker: " + ticker);

        result.setTicker(ticker);

        String name = dataEntryObject.get("name").getAsString();

        log.info("name: " + name);

        result.setName(name);

        String uniqueID = dataEntryObject.get("uniqueID").getAsString();

        log.info("uniqueID: " + uniqueID);

        result.setUniqueID(uniqueID);

        String exchangeCode = dataEntryObject.get("exchCode").getAsString();

        log.info("exchangeCode: " + exchangeCode);

        result.setExchangeCode(exchangeCode);

        String shareClassFIGI = dataEntryObject.get("shareClassFIGI").getAsString();

        log.info("shareClassFIGI: " + shareClassFIGI);

        result.setShareClassFIGI(shareClassFIGI);

        String compositeFigi = dataEntryObject.get("compositeFIGI").getAsString();

        log.info("compositeFigi: " + compositeFigi);

        result.setCompositeFIGI(compositeFigi);

        JsonElement uniqueIDFutOptElement = dataEntryObject.get("uniqueIDFutOpt");

        if (!uniqueIDFutOptElement.isJsonNull()) {

            String uniqueIDForFutureOption = uniqueIDFutOptElement.getAsString();

            log.info("uniqueIDFutOpt: " + uniqueIDForFutureOption);

            result.setUniqueIDForFutureOption(uniqueIDForFutureOption);
        }

        return result;
    }

    @Override
    public void write(JsonWriter writer, DataEntry dataEntry) throws IOException {
        throw new MethodNotSupportedException("The write method is not supported.");
    }
}
