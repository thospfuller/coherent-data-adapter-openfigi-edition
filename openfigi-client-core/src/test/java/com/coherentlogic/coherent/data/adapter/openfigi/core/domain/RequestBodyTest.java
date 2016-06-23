package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.ConstraintViolationException;

/**
 * Unit test for the {@link RequestBody} class.
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class RequestBodyTest {

    static final String TEST = "test";

    private RequestBody requestBody = null;

    @Before
    public void setUp() throws Exception {
        requestBody = new RequestBody(null);
    }

    @After
    public void tearDown() throws Exception {
        requestBody = null;
    }

    @Test
    public void testWithIsin() {

        MappingEntry me = requestBody.withIsin(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_ISIN, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithUniqueBloombergIdentifier() {

        MappingEntry me = requestBody.withUniqueBloombergIdentifier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_BB_UNIQUE, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithSedol() {

        MappingEntry me = requestBody.withSedol(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_SEDOL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithCommonCode() {

        MappingEntry me = requestBody.withCommonCode(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_COMMON, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithWertpapier() {

        MappingEntry me = requestBody.withWertpapier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_WERTPAPIER, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithCusip() {

        MappingEntry me = requestBody.withCusip(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_CUSIP, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithBb() {

        MappingEntry me = requestBody.withBb(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_BB, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithIdItaly() {

        MappingEntry me = requestBody.withItalianIdentifierNumber(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_ITALY, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithLocalExchangeSecuritySymbol() {

        MappingEntry me = requestBody.withLocalExchangeSecuritySymbol(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_EXCH_SYMBOL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithFullExchangeSymbol() {

        MappingEntry me = requestBody.withFullExchangeSymbol(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_FULL_EXCHANGE_SYMBOL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithCompositeFinancialInstrumentGlobalIdentifier() {

        MappingEntry me = requestBody.withCompositeFinancialInstrumentGlobalIdentifier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.COMPOSITE_ID_BB_GLOBAL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithShareClassFinancialInstrumentGlobalIdentifier() {

        MappingEntry me = requestBody
            .withShareClassFinancialInstrumentGlobalIdentifier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_BB_GLOBAL_SHARE_CLASS_LEVEL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithSecurityIdNumberDescription() {

        MappingEntry me = requestBody
            .withSecurityIdNumberDescription(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_BB_SEC_NUM_DES, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithFinancialInstrumentGlobalIdentifier() {

        MappingEntry me = requestBody
            .withFinancialInstrumentGlobalIdentifier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_BB_GLOBAL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithTicker() {

        MappingEntry me = requestBody
            .withTicker(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.TICKER, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithCusip8WithValidCusip() {

        MappingEntry me = requestBody
            .withCusip8("01234567").getMappingEntries().get(0);

        assertEquals(MappingEntry.ID_CUSIP_8_CHR, me.getIdType());
        assertEquals("01234567", me.getIdValue());
    }

    @Test(expected=ConstraintViolationException.class)
    public void testWithCusip8WithShortCusip() {

        MappingEntry me = requestBody
            .withCusip8("0123456").getMappingEntries().get(0);
    }

    @Test(expected=ConstraintViolationException.class)
    public void testWithCusip8WithLongCusip() {

        MappingEntry me = requestBody
            .withCusip8("0123456789").getMappingEntries().get(0);
    }

    @Test
    public void testWithOCCSymbol() {

        MappingEntry me = requestBody.withOCCSymbol(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.OCC_SYMBOL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithUniqueIdentifierForFutureOption() {

        MappingEntry me = requestBody.withUniqueIdentifierForFutureOption(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.UNIQUE_ID_FUT_OPT, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithOPRASymbol() {

        MappingEntry me = requestBody.withOPRASymbol(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.OPRA_SYMBOL, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }

    @Test
    public void testWithTradingSystemIdentifier() {

        MappingEntry me = requestBody.withTradingSystemIdentifier(TEST).getMappingEntries().get(0);

        assertEquals(MappingEntry.TRADING_SYSTEM_IDENTIFIER, me.getIdType());
        assertEquals(TEST, me.getIdValue());
    }
}
