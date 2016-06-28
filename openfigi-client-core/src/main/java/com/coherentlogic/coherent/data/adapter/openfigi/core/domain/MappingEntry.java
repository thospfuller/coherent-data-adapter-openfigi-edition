package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import static com.coherentlogic.coherent.data.model.core.util.Utils.assertNotNull;

import javax.persistence.Transient;

import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.InvalidMappingEntryConfigurationException;
import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * 
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class MappingEntry extends SerializableBean {

    private static final long serialVersionUID = -1786472253113680311L;

    public static final String ID_ISIN = "ID_ISIN",
        ID_BB_UNIQUE = "ID_BB_UNIQUE",
        ID_SEDOL = "ID_SEDOL",
        ID_COMMON = "ID_COMMON",
        ID_WERTPAPIER = "ID_WERTPAPIER",
        ID_CUSIP = "ID_CUSIP",
        ID_BB = "ID_BB",
        ID_ITALY = "ID_ITALY",
        ID_EXCH_SYMBOL = "ID_EXCH_SYMBOL",
        ID_FULL_EXCHANGE_SYMBOL = "ID_FULL_EXCHANGE_SYMBOL",
        COMPOSITE_ID_BB_GLOBAL = "COMPOSITE_ID_BB_GLOBAL",
        ID_BB_GLOBAL_SHARE_CLASS_LEVEL = "ID_BB_GLOBAL_SHARE_CLASS_LEVEL",
        ID_BB_SEC_NUM_DES = "ID_BB_SEC_NUM_DES",
        ID_BB_GLOBAL = "ID_BB_GLOBAL",
        TICKER = "TICKER",
        ID_CUSIP_8_CHR = "ID_CUSIP_8_CHR",
        OCC_SYMBOL = "OCC_SYMBOL",
        UNIQUE_ID_FUT_OPT = "UNIQUE_ID_FUT_OPT",
        OPRA_SYMBOL = "OPRA_SYMBOL",
        TRADING_SYSTEM_IDENTIFIER = "TRADING_SYSTEM_IDENTIFIER";

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

    /**
     * Used strictly to facilitate method chaining -- returns an instance of the {@link RequestBody}.
     */
    public RequestBody done () {
        return requestBody;
    }

    /**
     * Type of third party identifier. See Supported Identifiers for all supported 3rd party identifier types.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getIdType() {
        return idType;
    }

    public static final String ID_TYPE = "idType";

    /**
     * Type of third party identifier. See Supported Identifiers for all supported 3rd party identifier types.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setIdType(@Changeable(ID_TYPE) String idType) {
        this.idType = idType;
    }

    /**
     * Type of third party identifier. See Supported Identifiers for all supported 3rd party identifier types.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withIdType (String idType) {

        setIdType (idType);

        return this;
    }

    /**
     * The value for the represented third party identifier.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getIdValue() {
        return idValue;
    }

    public static final String ID_VALUE = "idValue";

    /**
     * The value for the represented third party identifier.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setIdValue(@Changeable(ID_VALUE) String idValue) {
        this.idValue = idValue;
    }

    /**
     * The value for the represented third party identifier.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setIdValue(Number idValue) {
        setIdValue(assertNotNull (ID_VALUE, idValue).toString());
    }

    /**
     * The value for the represented third party identifier.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withIdValue(String idValue) {

        setIdValue (idValue);

        return this;
    }

    /**
     * The value for the represented third party identifier.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withIdValue(Number idValue) {

        setIdValue (assertNotNull (ID_VALUE, idValue).toString());

        return this;
    }

    /**
     * An optional exchange code if it applies (cannot use with micCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    public static final String EXCHANGE_CODE = "exchangeCode";

    /**
     * An optional exchange code if it applies (cannot use with micCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setExchangeCode(@Changeable(EXCHANGE_CODE) String exchangeCode) {

        if (marketIdentificationCode != null && exchangeCode != null)
            throw new InvalidMappingEntryConfigurationException ("The marketIdentificationCode (micCode) must be "
                + "non-null when setting the exchangeCode (marketIdentificationCode: " + marketIdentificationCode
                + ").");

        this.exchangeCode = exchangeCode;
    }

    /**
     * An optional exchange code if it applies (cannot use with micCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withExchangeCode(String exchangeCode) {

        setExchangeCode(exchangeCode);

        return this;
    }

    /**
     * An optional ISO market identification code(MIC) if it applies(cannot use with exchCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getMarketIdentificationCode() {
        return marketIdentificationCode;
    }

    public static final String MARKET_IDENTIFICATION_CODE = "marketIdentificationCode";

    /**
     * An optional ISO market identification code(MIC) if it applies(cannot use with exchCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setMarketIdentificationCode(@Changeable(MARKET_IDENTIFICATION_CODE) String marketIdentificationCode) {

        if (exchangeCode != null && marketIdentificationCode != null)
            throw new InvalidMappingEntryConfigurationException ("The exchangeCode (exchCode) must be "
                + "non-null when setting the marketIdentificationCode (exchangeCode: " + exchangeCode + ").");

        this.marketIdentificationCode = marketIdentificationCode;
    }

    /**
     * An optional ISO market identification code(MIC) if it applies(cannot use with exchCode).
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withMarketIdentificationCode(String marketIdentificationCode) {

        setMarketIdentificationCode(marketIdentificationCode);

        return this;
    }

    /**
     * An optional currency if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getCurrency() {
        return currency;
    }

    public static final String CURRENCY = "currency";

    /**
     * An optional currency if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setCurrency(@Changeable(CURRENCY) String currency) {
        this.currency = currency;
    }

    /**
     * An optional currency if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withCurrency(String currency) {

        setCurrency(currency);

        return this;
    }

    /**
     * An optional market sector description if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public String getMarketSectorDescription() {
        return marketSectorDescription;
    }

    public static final String MARKET_SECTOR_DESCRIPTION = "marketSectorDescription";

    /**
     * An optional market sector description if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public void setMarketSectorDescription(@Changeable(MARKET_SECTOR_DESCRIPTION) String marketSectorDescription) {
        this.marketSectorDescription = marketSectorDescription;
    }

    /**
     * An optional market sector description if it applies.
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public MappingEntry withMarketSectorDescription(String marketSectorDescription) {

        setMarketSectorDescription(marketSectorDescription);

        return this;
    }

    @Override
    public String toString() {
        return "MappingEntry [idType=" + idType + ", idValue=" + idValue + ", exchangeCode=" + exchangeCode
            + ", marketIdentificationCode=" + marketIdentificationCode + ", currency=" + currency
            + ", marketSectorDescription=" + marketSectorDescription + "]";
    }
}