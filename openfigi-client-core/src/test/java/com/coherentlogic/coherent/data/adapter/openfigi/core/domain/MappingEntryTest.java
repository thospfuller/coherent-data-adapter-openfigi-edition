package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.InvalidMappingEntryConfigurationException;

/**
 * Unit test for the {@link MappingEntry} class.
 */
public class MappingEntryTest {

    private static final String TEST = "test";

    private MappingEntry mappingEntry = null;

    @Before
    public void setUp() throws Exception {
        mappingEntry = new MappingEntry(new RequestBody (null));
    }

    @After
    public void tearDown() throws Exception {
        mappingEntry = null;
    }

    @Test
    public void testSetExchangeCodeToNonNullValue() {
        mappingEntry.setExchangeCode(TEST);
    }

    @Test
    public void testSetExchangeCodeToNullValue() {
        mappingEntry.setExchangeCode(null);
    }

    @Test(expected=InvalidMappingEntryConfigurationException.class)
    public void testSetExchangeCodeWithMisconfiguration() {
        mappingEntry.setMarketIdentificationCode(TEST);
        mappingEntry.setExchangeCode(TEST);
    }

    @Test
    public void testSetMarketIdentificationCodeToNonNullValue() {
        mappingEntry.setMarketIdentificationCode(TEST);
    }

    @Test
    public void testSetMarketIdentificationCodeToNullValue() {
        mappingEntry.setMarketIdentificationCode(null);
    }

    @Test(expected=InvalidMappingEntryConfigurationException.class)
    public void testSetMarketIdentificationCodeWithMisconfiguration() {
        mappingEntry.setExchangeCode(TEST);
        mappingEntry.setMarketIdentificationCode(TEST);
    }
}
