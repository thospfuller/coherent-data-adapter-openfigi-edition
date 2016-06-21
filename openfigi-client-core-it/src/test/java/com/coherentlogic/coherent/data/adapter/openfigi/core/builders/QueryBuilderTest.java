package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;

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
        assertEquals(1, data.getEntries().size());
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
        .doGet(Data.class);

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
        .doGet(Data.class);

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
        .doGet(Data.class);

        List<ErrorEntry> errorEntries = (List<ErrorEntry>) data.getEntries().get(0);

        assertNotNull (data);
        assertEquals(1, data.getEntries().size());
        assertEquals("No identifier found.", errorEntries.get(0).getError());
    }
}
