package com.coherentlogic.coherent.data.adapter.openfigi.client.db.integration.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

//    @Before
//    public void setUp () {
//        this.dataEntryDAO = applicationContext.getBean(DataEntryDAO.class);
//        this.errorEntryDAO = applicationContext.getBean(ErrorEntryDAO.class);
//        this.queryBuilder = applicationContext.getBean(QueryBuilder.class);
//    }

    @After
    public void tearDown() throws Exception {
//        applicationContext = null;
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
            for (SerializableBean<?> nextEntry : nextEntries) {
                if (nextEntry instanceof DataEntry) {

                    nextEntry.addPropertyChangeListener(
                        event -> {
                            System.out.println ("ZZZZZZ>>>! " + event);
                        }
                    );

                    dataEntryDAO.persist((DataEntry) nextEntry);

                } else if (nextEntry instanceof ErrorEntry) {

                    nextEntry.addPropertyChangeListener(
                        event -> {
                            System.out.println ("FEEEEE>>>! " + event);
                        }
                    );

                    errorEntryDAO.persist((ErrorEntry) nextEntry);
                }
            }
        }
    }

//    @Test
//    public void reviewCRUDOperations () {
//
//        List<Category> categoryList = categories.getCategoryList();
//
//        Category firstCategory = categoryList.get(0);
//
//        assertNull (firstCategory.getPrimaryKey());
//        assertNotNull (categoryList);
//        assertEquals (2, categoryList.size());
//
//        categoriesDAO.persist(categories);
//
//        Long uniqueId = categories.getPrimaryKey();
//
//        assertNotNull (uniqueId);
//
//        Categories persistedCategories = categoriesDAO.find(uniqueId);
//
//        List<Category> persistedCategoryList =
//            persistedCategories.getCategoryList();
//
//        assertNotNull (persistedCategories);
//        assertEquals (2, persistedCategoryList.size());
//
//        persistedCategoryList.remove(0);
//
//        categoriesDAO.merge(persistedCategories);
//
//        Categories mergedPersistedCategories = categoriesDAO.find(uniqueId);
//
//        persistedCategoryList = mergedPersistedCategories.getCategoryList();
//
//        assertEquals (1, persistedCategoryList.size());
//
//        categoriesDAO.remove(mergedPersistedCategories);
//
//        Categories nullCategories = categoriesDAO.find(uniqueId);
//
//        assertNull (nullCategories);
//    }
}
