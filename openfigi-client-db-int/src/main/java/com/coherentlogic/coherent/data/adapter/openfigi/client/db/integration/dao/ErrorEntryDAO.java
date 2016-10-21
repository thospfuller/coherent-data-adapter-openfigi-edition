package com.coherentlogic.coherent.data.adapter.openfigi.client.db.integration.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.coherent.data.adapter.core.db.integration.dao.SerializableDAO;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;

/**
 * Data access pattern implementation for {@link ErrorEntry} objects.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@Repository(ErrorEntryDAO.ERROR_ENTRY_DAO)
@Transactional
public class ErrorEntryDAO extends SerializableDAO<ErrorEntry> {

    public static final String ERROR_ENTRY_DAO = "errorEntryDAO";

    @Override
    public ErrorEntry find(long primaryKey) {
        return find (ErrorEntry.class, primaryKey);
    }
}
