package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coherentlogic.coherent.data.adapter.openfigi.core.configuration.GlobalConfiguration;
import com.coherentlogic.coherent.data.adapter.openfigi.core.configuration.XMLConfiguration;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GlobalConfiguration.class, XMLConfiguration.class})
//@SpringBootApplication(scanBasePackages = {"com.coherentlogic.coherent.data.adapter.openfigi"})
public class QueryBuilderTest {

    public static final String OPEN_FIGI_API_KEY = "OPEN_FIGI_API_KEY", FOO = "foo";

    /**
     * This value should be set in the environment properties of the operating
     * system. Make sure to restart your IDE and/or OS shell once this has been
     * set.
     */
    static final String API_KEY = System.getenv(OPEN_FIGI_API_KEY);

    @Autowired
    private QueryBuilder queryBuilder;

    @After
    public void tearDown () {
        this.queryBuilder = null;
    }

    @Test
    public void testGetWithValidRequest1 () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .withWertpapier("851399")
            .done()
        .doGet();

        assertNotNull (data);
        assertTrue(1 <= data.getEntries().size());

        List<? extends SerializableBean> dataEntries = data.getEntries().get(0);

        for (SerializableBean next : dataEntries) {
            assertTrue (next instanceof DataEntry);
        }
    }

    @Test
    public void testGetWithValidRequest2 () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doGet();

        assertNotNull (data);
        assertTrue(1 <= data.getEntries().size());

        List<? extends SerializableBean> dataEntries = data.getEntries().get(0);

        for (SerializableBean next : dataEntries)
            assertTrue (next instanceof DataEntry);
    }

    @Test
    public void testGetWithLargeValidRequest () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .newMappingEntry()
                    .withIdType("ID_ISIN")
                    .withIdValue("US4592001014")
                .done()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doGet();

        assertNotNull (data);
        assertTrue(2 <= data.getEntries().size());
    }

    @Test
    public void testGetWithLargeValidRequestAndOneInvalidRequest () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .newMappingEntry()
                    .withIdType("ID_ISIN")
                    .withIdValue("US4592001014")
                .done()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("INVALID")
                .done()
            .done()
        .doGet();

        assertNotNull (data);

        List<List<? extends SerializableBean>> entries = data.getEntries();

        assertTrue(3 <= entries.size());

        DataEntry dataEntry = (DataEntry) entries.get(0).get(0);

        List<ErrorEntry> errorEntries = (List<ErrorEntry>) entries.get(7);

        ErrorEntry errorEntry = errorEntries.get(0);

        assertEquals("No identifier found.", errorEntry.getError());

        checkDataEntryPCLEventGeneration (dataEntry);
        checkErrorEntryPCLEventGeneration (errorEntry);
    }

    static void checkDataEntryPCLEventGeneration (DataEntry dataEntry) {

        AtomicBoolean flag = new AtomicBoolean();

        dataEntry.addPropertyChangeListener(
            event -> {
                flag.set(true);
            }
        );

        dataEntry.setCompositeFIGI(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setExchangeCode(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setFigi(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setMarketSector(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setName(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setSecurityType(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setShareClassFIGI(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setTicker(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setUniqueID(FOO);

        assertTrue (flag.get());

        flag.set(false);

        dataEntry.setUniqueIDForFutureOption(FOO);

        assertTrue (flag.get());
    }

    static void checkErrorEntryPCLEventGeneration (ErrorEntry errorEntry) {

        AtomicBoolean flag = new AtomicBoolean();

        errorEntry.addPropertyChangeListener(
            event -> {
                flag.set(true);
            }
        );

        errorEntry.setError(FOO);

        assertTrue (flag.get());
    }

    @Test
    public void testGetWithInvalidIdValue () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399XXX")
                .done()
            .done()
        .doGet();

        List<ErrorEntry> errorEntries = (List<ErrorEntry>) data.getEntries().get(0);

        assertNotNull (data);
        assertTrue(1 <= data.getEntries().size());
//        assertEquals("No identifier found.", errorEntries.get(0).getError());
    }
}
