package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import javax.persistence.Transient;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.datafeed.annotations.Changeable;

/**
 * 
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class MappingEntry extends SerializableBean {

    private static final long serialVersionUID = -1786472253113680311L;

    /**
     * Type of third party identifier. See Supported Identifiers for all supported 3rd party identifier types.
     */
    private String idType = null;

    /**
     * The value for the represented third party identifier.
     */
    private String idValue = null;

    /**
     * An optional exchange code if it applies(cannot use with micCode).
     */
    private String exchangeCode = null;

    /**
     * An optional ISO market identification code(MIC) if it applies(cannot use with exchCode).
     */
    private String marketIdentificationCode= null;

    /**
     * An optional currency if it applies.
     */
    private String currency = null;

    /**
     * An optional market sector description if it applies.
     */
    private String marketSectorDescription = null;

    @Transient
    private transient final RequestBody requestBody;

    public MappingEntry(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public RequestBody done () {
        return requestBody;
    }

    public String getIdType() {
        return idType;
    }

    public static final String ID_TYPE = "idType";

    public void setIdType(@Changeable(ID_TYPE) String idType) {
        this.idType = idType;
    }

    public MappingEntry withIdType (String idType) {

        setIdType (idType);

        return this;
    }

    public String getIdValue() {
        return idValue;
    }

    public static final String ID_VALUE = "idValue";

    public void setIdValue(@Changeable(ID_VALUE) String idValue) {
        this.idValue = idValue;
    }

    public MappingEntry withIdValue(String idValue) {

        setIdValue (idValue);

        return this;
    }

    public MappingEntry withIdValue(Number idValue) {

        setIdValue (idValue);

        return this;
    }

    public void setIdValue(Number idValue) {
        setIdValue(idValue.toString());
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public static final String EXCHANGE_CODE = "exchangeCode";

    public void setExchangeCode(@Changeable(EXCHANGE_CODE) String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getMarketIdentificationCode() {
        return marketIdentificationCode;
    }

    public static final String MARKET_IDENTIFICATION_CODE = "marketIdentificationCode";

    public void setMarketIdentificationCode(@Changeable(MARKET_IDENTIFICATION_CODE) String marketIdentificationCode) {
        this.marketIdentificationCode = marketIdentificationCode;
    }

    public String getCurrency() {
        return currency;
    }

    public static final String CURRENCY = "currency";

    public void setCurrency(@Changeable(CURRENCY) String currency) {
        this.currency = currency;
    }

    public String getMarketSectorDescription() {
        return marketSectorDescription;
    }

    public static final String MARKET_SECTOR_DESCRIPTION = "marketSectorDescription";

    public void setMarketSectorDescription(@Changeable(MARKET_SECTOR_DESCRIPTION) String marketSectorDescription) {
        this.marketSectorDescription = marketSectorDescription;
    }

    @Override
    public String toString() {
        return "MappingEntry [idType=" + idType + ", idValue=" + idValue + ", exchangeCode=" + exchangeCode
            + ", marketIdentificationCode=" + marketIdentificationCode + ", currency=" + currency
            + ", marketSectorDescription=" + marketSectorDescription + "]";
    }
}