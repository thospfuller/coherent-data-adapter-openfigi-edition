package com.coherentlogic.coherent.data.adapter.openfigi.core.converters;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.MappingEntry;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class MappingEntryConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return MappingEntry.class.equals(type);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {

        MappingEntry mappingEntry = (MappingEntry) source;

        writer.addAttribute(MappingEntry.ID_TYPE, mappingEntry.getIdType());
        writer.addAttribute(MappingEntry.ID_VALUE, mappingEntry.getIdValue());

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        throw new UnsupportedOperationException();
    }
}
