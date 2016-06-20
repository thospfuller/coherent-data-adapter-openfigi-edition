package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coherentlogic.coherent.data.adapter.openfigi.core.configuration.GlobalConfiguration;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GlobalConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.coherentlogic.estimize.client"})
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

    /**
     * Running these tests back-to-back can result in a 404.
     */
    @Test
    public void testGetWithValidRequest () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .clear()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doGet(Data.class);

        assertNotNull (data);
        assertEquals(134, data.getEntries().size());
    }

    /**
     * Running these tests back-to-back can result in a 404.
     */
    @Test
    public void testGetWithLargeValidRequest () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .clear()
                .newMappingEntry()
                    .withIdType("ID_ISIN")
                    .withIdValue("US4592001014")
                .done()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399")
                .done()
            .done()
        .doGet(Data.class);

        assertNotNull (data);
        assertEquals(268, data.getEntries().size());
    }

    /**
     * Running these tests back-to-back can result in a 404.
     */
    @Test
    public void testGetWithLargeValidRequestAndOneInvalidRequest () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .clear()
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
        .doGet(Data.class);

        assertNotNull (data);
        assertEquals(269, data.getEntries().size());

        ErrorEntry errorEntry = (ErrorEntry) data.getEntries().get(268);

        assertEquals("No identifier found.", errorEntry.getError());
    }

    /**
     * Running these tests back-to-back can result in a 404.
     */
    @Test
    public void testGetWithInvalidIdValue () {

        Data data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .clear()
                .newMappingEntry()
                    .withIdType("ID_WERTPAPIER")
                    .withIdValue("851399XXX")
                .done()
            .done()
        .doGet(Data.class);

        ErrorEntry errorEntry = (ErrorEntry) data.getEntries().get(0);

        assertNotNull (data);
        assertEquals(1, data.getEntries().size());
        assertEquals("No identifier found.", errorEntry.getError());
    }
}