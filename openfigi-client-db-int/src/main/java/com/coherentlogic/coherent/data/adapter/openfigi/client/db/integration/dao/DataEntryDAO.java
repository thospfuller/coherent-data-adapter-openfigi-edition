package com.coherentlogic.coherent.data.adapter.openfigi.client.db.integration.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.coherent.data.adapter.core.db.integration.dao.SerializableDAO;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;

/**
 * Data access pattern implementation for {@link Data} objects.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(DataEntryDAO.DATA_ENTRY_DAO)
@Transactional
public class DataEntryDAO extends SerializableDAO<DataEntry> {

    public static final String DATA_ENTRY_DAO = "dataEntryDAO";

    @Override
    public DataEntry find(long primaryKey) {
        return find (DataEntry.class, primaryKey);
    }

//    public void persist(Collection<DataEntry> dataEntries) {
//        for (DataEntry nextDataEntry : dataEntries)
//            persist(nextDataEntry);
//    }

    
}
