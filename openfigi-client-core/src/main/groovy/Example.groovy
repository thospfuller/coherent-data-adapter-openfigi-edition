@Grab(group='log4j', module='log4j', version='1.2.17')
@Grab(group='com.coherentlogic.coherent.data.adapter.openfigi', module='openfigi-client-core', version='0.8.5-RELEASE')

// ********************************************************************************
// *** NOTE: There's something wrong with how Jettison is being resolved by     ***
// ***       Grape and the temporary solution, which is ugly (but works), is to ***
// ***       copy the Jettison dependency into the Groovy lib directory, which  ***
// ***       we've done here simply to get this working.                        ***
// ********************************************************************************
@Grab(group='org.codehaus.jettison', module='jettison', version='1.3.7')

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder
import org.springframework.core.io.ByteArrayResource
import org.springframework.context.support.GenericXmlApplicationContext

def applicationContextDefinition = '''
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config />
    <context:component-scan base-package="com.coherentlogic.coherent.data.adapter.openfigi" />
</beans>
'''

def resource = new ByteArrayResource(applicationContextDefinition.getBytes())

def applicationContext = new GenericXmlApplicationContext()

applicationContext.registerShutdownHook()

applicationContext.load (resource)

public class OpenFIGIExample {

    def OPEN_FIGI_API_KEY = "OPEN_FIGI_API_KEY";

    /**
     * This value should be set in the environment properties of the operating
     * system. Make sure to restart your IDE and/or OS shell once this has been
     * set.
     */
    def API_KEY = System.getenv(OPEN_FIGI_API_KEY);

    private def queryBuilder;

    public void query () {

        def data = queryBuilder
            .withApiKey(API_KEY)
            .getRequestBody()
                .withIsin("US4592001014")
                .withWertpapier("851399")
            .done()
        .doGet();

        def dataEntries = data.getEntries().get(0);

        def ctr = 0

        for (def next : dataEntries) {
            System.out.println("next[" + (ctr++) + "]: " + next);
        }
    }
}

applicationContext.refresh ()

def queryBuilder = applicationContext.getBean (QueryBuilder.class)

def openFIGIExample = new OpenFIGIExample ()

openFIGIExample.queryBuilder = queryBuilder

openFIGIExample.query ()