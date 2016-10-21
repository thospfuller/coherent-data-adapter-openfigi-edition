package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.ConstraintViolationException;
import com.coherentlogic.coherent.data.model.core.util.Utils;

/**
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class RequestBody {

    public static final String OPEN_FIGI_REQUEST_BODY = "openFIGIRequestBody";

    public static final int CUSIP_8_LENGTH = 8;

    private final List<MappingEntry> mappingEntries = new ArrayList<MappingEntry> ();

    private transient final QueryBuilder queryBuilder;

    public RequestBody(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public QueryBuilder done () {
        return queryBuilder;
    }

    public List<MappingEntry> getMappingEntries() {
        return mappingEntries;
    }

    public RequestBody addMappingEntry(MappingEntry mappingEntry) {

        getMappingEntries ().add(mappingEntry);

        return this;
    }

    public MappingEntry newMappingEntry () {

        MappingEntry mappingEntry = new MappingEntry (this);

        addMappingEntry(mappingEntry);

        return mappingEntry;
    }

    /**
     * ID_ISIN
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withIsin (String isin) {

        newMappingEntry().withIdType(MappingEntry.ID_ISIN).withIdValue(isin);

        return this;
    }

    /**
     * ID_BB_UNIQUE
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withUniqueBloombergIdentifier (String idBbUnique) {

        newMappingEntry().withIdType(MappingEntry.ID_BB_UNIQUE).withIdValue(idBbUnique);

        return this;
    }

    /**
     * ID_SEDOL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withSedol (String sedol) {

        newMappingEntry().withIdType(MappingEntry.ID_SEDOL).withIdValue(sedol);

        return this;
    }

    /**
     * ID_COMMON
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withCommonCode (String common) {

        newMappingEntry().withIdType(MappingEntry.ID_COMMON).withIdValue(common);

        return this;
    }

    /**
     * ID_WERTPAPIER
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withWertpapier (String idWertpapier) {

        newMappingEntry().withIdType(MappingEntry.ID_WERTPAPIER).withIdValue(idWertpapier);

        return this;
    }

    /**
     * ID_CUSIP
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withCusip (String cusip) {

        newMappingEntry().withIdType(MappingEntry.ID_CUSIP).withIdValue(cusip);

        return this;
    }

    /**
     * ID_BB
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withBb (String bb) {

        newMappingEntry().withIdType(MappingEntry.ID_BB).withIdValue(bb);

        return this;
    }

    /**
     * ID_ITALY
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withItalianIdentifierNumber (String italianIdentifierNumber) {

        newMappingEntry().withIdType(MappingEntry.ID_ITALY).withIdValue(italianIdentifierNumber);

        return this;
    }

    /**
     * ID_ITALY
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withItalianIdentifierNumber (Number italianIdentifierNumber) {

        newMappingEntry().withIdType(MappingEntry.ID_ITALY).withIdValue(italianIdentifierNumber);

        return this;
    }

    /**
     * ID_EXCH_SYMBOL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withLocalExchangeSecuritySymbol (String localExchangeSecuritySymbol) {

        newMappingEntry().withIdType(MappingEntry.ID_EXCH_SYMBOL).withIdValue(localExchangeSecuritySymbol);

        return this;
    }

    /**
     * ID_FULL_EXCHANGE_SYMBOL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withFullExchangeSymbol (String fullExchangeSymbol) {

        newMappingEntry().withIdType(MappingEntry.ID_FULL_EXCHANGE_SYMBOL).withIdValue(fullExchangeSymbol);

        return this;
    }

    /**
     * COMPOSITE_ID_BB_GLOBAL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withCompositeFinancialInstrumentGlobalIdentifier (
        String compositeFinancialInstrumentGlobalIdentifier) {

        newMappingEntry()
            .withIdType(MappingEntry.COMPOSITE_ID_BB_GLOBAL).withIdValue(compositeFinancialInstrumentGlobalIdentifier);

        return this;
    }

    /**
     * ID_BB_GLOBAL_SHARE_CLASS_LEVEL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withShareClassFinancialInstrumentGlobalIdentifier (
        String shareClassFinancialInstrumentGlobalIdentifier) {

        newMappingEntry()
            .withIdType(MappingEntry.ID_BB_GLOBAL_SHARE_CLASS_LEVEL)
            .withIdValue(shareClassFinancialInstrumentGlobalIdentifier);

        return this;
    }

    /**
     * ID_BB_SEC_NUM_DES
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withSecurityIdNumberDescription (String securityIdNumberDescription) {

        newMappingEntry().withIdType(MappingEntry.ID_BB_SEC_NUM_DES).withIdValue(securityIdNumberDescription);

        return this;
    }

    /**
     * ID_BB_GLOBAL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withFinancialInstrumentGlobalIdentifier (String financialInstrumentGlobalIdentifier) {

        newMappingEntry().withIdType(MappingEntry.ID_BB_GLOBAL).withIdValue(financialInstrumentGlobalIdentifier);

        return this;
    }

    /**
     * TICKER
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withTicker (String ticker) {

        newMappingEntry().withIdType(MappingEntry.TICKER).withIdValue(ticker);

        return this;
    }

    public static final String CUSIP_8 = "cusip8";

    /**
     * ID_CUSIP_8_CHR
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withCusip8 (String cusip8) {

        if (((String)Utils.assertNotNull (CUSIP_8, cusip8)).length() != CUSIP_8_LENGTH)
            throw new ConstraintViolationException("The cusip8 value must be of length 8 (cusip8: " + cusip8 +
                ", length: " + cusip8.length() + ").");

        newMappingEntry().withIdType(MappingEntry.ID_CUSIP_8_CHR).withIdValue(cusip8);

        return this;
    }

    /**
     * OCC_SYMBOL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withOCCSymbol (String occSymbol) {

        newMappingEntry().withIdType(MappingEntry.OCC_SYMBOL).withIdValue(occSymbol);

        return this;
    }

    /**
     * UNIQUE_ID_FUT_OPT
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withUniqueIdentifierForFutureOption (String uniqueIdentifierForFutureOption) {

        newMappingEntry().withIdType(MappingEntry.UNIQUE_ID_FUT_OPT).withIdValue(uniqueIdentifierForFutureOption);

        return this;
    }

    /**
     * OPRA_SYMBOL
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withOPRASymbol (String opraSymbol) {

        newMappingEntry().withIdType(MappingEntry.OPRA_SYMBOL).withIdValue(opraSymbol);

        return this;
    }

    /**
     * TRADING_SYSTEM_IDENTIFIER
     *
     * @see <a href="https://www.openfigi.com/api">OpenFIGI API</a>
     */
    public RequestBody withTradingSystemIdentifier (String tradingSystemIdentifier) {

        newMappingEntry().withIdType(MappingEntry.TRADING_SYSTEM_IDENTIFIER).withIdValue(tradingSystemIdentifier);

        return this;
    }

    public RequestBody clear () {

        getMappingEntries ().clear();

        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((mappingEntries == null) ? 0 : mappingEntries.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestBody other = (RequestBody) obj;
        if (mappingEntries == null) {
            if (other.mappingEntries != null)
                return false;
        } else if (!mappingEntries.equals(other.mappingEntries))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RequestBody [mappingEntries=" + mappingEntries + "]";
    }
}
