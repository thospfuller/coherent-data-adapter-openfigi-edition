package com.coherentlogic.coherent.data.adapter.openfigi.core.hibernate;

import org.hibernate.EntityNameResolver;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.EntityNameResolutionRuntimeException;

/**
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class CGLIBEnhancedObjectTuplizer extends PojoEntityTuplizer {

    public CGLIBEnhancedObjectTuplizer(EntityMetamodel entityMetamodel, PersistentClass persistentClass) {
        super(entityMetamodel, persistentClass);
    }

    public EntityNameResolver[] getEntityNameResolvers() {

        return new EntityNameResolver[] {
            OpenFIGIEntityNameResolver.SINGLETON
        };
    }
}

class OpenFIGIEntityNameResolver implements EntityNameResolver {

    public static final OpenFIGIEntityNameResolver SINGLETON = new OpenFIGIEntityNameResolver ();

    @Override
    public String resolveEntityName(Object object) {

        String result = null;

        if (object instanceof DataEntry) {
            result = DataEntry.class.getName();
        } else if (object instanceof ErrorEntry) {
            result = ErrorEntry.class.getName();
        } else
            throw new EntityNameResolutionRuntimeException (object);

        System.out.println("result: " + result + ", for: " + object);

        return result;
    }
}
