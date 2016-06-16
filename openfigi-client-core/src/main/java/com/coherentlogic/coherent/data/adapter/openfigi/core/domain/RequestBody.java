package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class RequestBody extends SerializableBean {

    private static final long serialVersionUID = 1177536240623915122L;

    private final List<MappingEntry> mappingEntries = new ArrayList<MappingEntry> ();

    private transient final QueryBuilder queryBuilder;

    public RequestBody(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public QueryBuilder done () {
        return queryBuilder;
    }

    public List<MappingEntry> getMappingEntries() {
        return mappingEntries;
    }

    public RequestBody addMappingEntry(MappingEntry mappingEntry) {

        getMappingEntries ().add(mappingEntry);

        return this;
    }

    public MappingEntry newMappingEntry () {

        MappingEntry mappingEntry = new MappingEntry (this);

        addMappingEntry(mappingEntry);

        return mappingEntry;
    }

    public RequestBody clear () {

        getMappingEntries ().clear();

        return this;
    }
}
