package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication(scanBasePackages = {"com.coherentlogic.coherent.data.adapter.openfigi"})
public class QueryBuilderTest {

    public static final String OPEN_FIGI_API_KEY = "OPEN_FIGI_API_KEY";

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
        assertEquals(1, data.getEntries().size());

        List<? extends SerializableBean> dataEntries = data.getEntries().get(0);

        for (SerializableBean next : dataEntries)
            assertTrue (next instanceof DataEntry);
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
        assertEquals(1, data.getEntries().size());

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
        assertEquals(2, data.getEntries().size());
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
        assertEquals(3, data.getEntries().size());

        List<ErrorEntry> errorEntries = (List<ErrorEntry>) data.getEntries().get(2);

        assertEquals("No identifier found.", errorEntries.get(0).getError());
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
        assertEquals(1, data.getEntries().size());
        assertEquals("No identifier found.", errorEntries.get(0).getError());
    }
}
