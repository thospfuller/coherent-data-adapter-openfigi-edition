package com.coherentlogic.coherent.data.adapter.openfigi.client.db.integration.dao;

import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.configuration.GlobalConfiguration;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * Unit test for the {@link DataEntryDAO} class.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(classes={GlobalConfiguration.class, TestXMLConfiguration.class})
public class DataDAOTest {

    public static final String OPEN_FIGI_API_KEY = "OPEN_FIGI_API_KEY";

    /**
     * This value should be set in the environment properties of the operating
     * system. Make sure to restart your IDE and/or OS shell once this has been
     * set.
     */
    static final String API_KEY = System.getenv(OPEN_FIGI_API_KEY);

    @Autowired
    private DataEntryDAO dataEntryDAO;

    @Autowired
    private ErrorEntryDAO errorEntryDAO;

    @Autowired
    private QueryBuilder queryBuilder;

    @After
    public void tearDown() throws Exception {
        dataEntryDAO = null;
        errorEntryDAO = null;
        queryBuilder = null;
    }

    @Test
    public void reviewCRUDOperations () {

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

        // See also ScopedBeanInterceptor

        for (List<? extends SerializableBean> nextEntries : data.getEntries()) {
            if (0 < nextEntries.size()) {
                if (nextEntries.get(0) instanceof DataEntry) {
                    dataEntryDAO.persist((Collection<DataEntry>) nextEntries);
                } else {
                    errorEntryDAO.persist((Collection<ErrorEntry>) nextEntries);
                }
            }
        }
    }
}
