package com.coherentlogic.coherent.data.adapter.openfigi.core.hibernate;

import org.hibernate.EntityNameResolver;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.exceptions.EntityNameResolutionRuntimeException;

/**
 * This class maps enhanced classes to their proper (Hibernate-registered) class.
 *
 * This framework enhances the domain model using AOP and this can cause a problem when with JPA/Hibernate (in
 * particular) because Hibernate does not recognize a class with name ie. DataEntry$$EnhancerBySpringCGLIB$$65cfe30a so
 * without this class we'll see exceptions such as what appears below.
 * 
 * java.lang.IllegalArgumentException: Unknown entity:
 * com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry$$EnhancerBySpringCGLIB$$65cfe30a
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

        return result;
    }
}
