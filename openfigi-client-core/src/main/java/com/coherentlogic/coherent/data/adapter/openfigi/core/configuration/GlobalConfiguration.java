package com.coherentlogic.coherent.data.adapter.openfigi.core.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.converters.RequestBodyConverter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.MappingEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.model.core.xstream.CustomMarshallingStrategy;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@Configuration
public class GlobalConfiguration {

    static final String OPEN_FIGI_XSTREAM_MARSHALLER = "openFIGIXStreamMarshaller",
        OPEN_FIGI_REST_TEMPLATE = "openFIGIRestTemplate",
        OPEN_FIGI_QUERY_BUILDER = "openFIGIQueryBuilder";

    @Bean(name=OPEN_FIGI_REST_TEMPLATE)
    public RestTemplate getRestTemplate (@Qualifier(OPEN_FIGI_XSTREAM_MARSHALLER) XStreamMarshaller xStreamMarshaller) {

//        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter ();
//        marshallingHttpMessageConverter.setMarshaller(xStreamMarshaller);
//        marshallingHttpMessageConverter.setUnmarshaller(xStreamMarshaller);
//        marshallingHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>> ();

        messageConverters.add(new RequestBodyConverter ());
//        messageConverters.add(marshallingHttpMessageConverter);

        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    /**
     * @return
     * @throws IOException
     */
    @Bean(name=OPEN_FIGI_XSTREAM_MARSHALLER)
    public XStreamMarshaller getXStreamMarshaller () throws IOException {

        XStreamMarshaller result = new XStreamMarshaller ();

        result.setMarshallingStrategy(new CustomMarshallingStrategy ());

        result.setStreamDriver(new JettisonMappedXmlDriver());
        result.setAnnotatedClasses(
            Data.class,
            MappingEntry.class,
            RequestBody.class
        );

        return result;
    }

    @Bean(name=OPEN_FIGI_QUERY_BUILDER)
    public QueryBuilder getQueryBuilder (@Qualifier(OPEN_FIGI_REST_TEMPLATE) RestTemplate restTemplate) {
        return new QueryBuilder (restTemplate);
    }
}
