package com.coherentlogic.coherent.data.adapter.openfigi.core.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.DataEntryTypeAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.DataTypeAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.ErrorEntryTypeAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.builders.QueryBuilder;
import com.coherentlogic.coherent.data.adapter.openfigi.core.converters.RequestBodyConverter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.DataEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.ErrorEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.MappingEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.adapter.openfigi.core.extractors.DataExtractor;
import com.coherentlogic.coherent.data.adapter.openfigi.core.factories.DataEntryFactory;
import com.coherentlogic.coherent.data.adapter.openfigi.core.factories.DataFactory;
import com.coherentlogic.coherent.data.adapter.openfigi.core.factories.ErrorEntryFactory;
import com.coherentlogic.coherent.data.model.core.factories.TypedFactory;
import com.coherentlogic.coherent.data.model.core.xstream.CustomMarshallingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * 
 * @author <a href="support@coherentlogic.com">Support</a>
 */
@Configuration
public class GlobalConfiguration {

    public static final String OPEN_FIGI_XSTREAM_MARSHALLER = "openFIGIXStreamMarshaller",
        OPEN_FIGI_REST_TEMPLATE = "openFIGIRestTemplate",
        OPEN_FIGI_QUERY_BUILDER = "openFIGIQueryBuilder",
        GSON_BUILDER = "gsonBuilder", VERSION = "version";

    @Bean(name=OPEN_FIGI_REST_TEMPLATE)
    public RestTemplate getRestTemplate (@Qualifier(OPEN_FIGI_XSTREAM_MARSHALLER) XStreamMarshaller xStreamMarshaller) {

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>> ();

        messageConverters.add(new RequestBodyConverter ());

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

        result.getXStream().autodetectAnnotations(true);

        result.getXStream().omitField(Data.class, VERSION);
        result.getXStream().omitField(MappingEntry.class, VERSION);
        result.getXStream().omitField(RequestBody.class, VERSION);

        return result;
    }

    @Bean(name=OPEN_FIGI_QUERY_BUILDER)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public QueryBuilder getQueryBuilder (
        @Qualifier(OPEN_FIGI_REST_TEMPLATE) RestTemplate restTemplate,
        @Qualifier(DataExtractor.BEAN_NAME) ResponseExtractor<Data> dataExtractor
    ) {
        return new QueryBuilder (restTemplate, dataExtractor);
    }

    @Bean(name=DataExtractor.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ResponseExtractor<Data> getDataExtractor (
        @Qualifier(GSON_BUILDER) GsonBuilder gsonBuilder,
        @Qualifier(DataTypeAdapter.BEAN_NAME) TypeAdapter<Data> dataTypeAdapter
    ) {
        return new DataExtractor(gsonBuilder, dataTypeAdapter);
    }

    @Bean(name=DataTypeAdapter.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TypeAdapter<Data> getDataTypeAdapter (
        @Qualifier(GlobalConfiguration.GSON_BUILDER) GsonBuilder gsonBuilder,
        @Autowired TypedFactory<Data> dataFactory,
        @Qualifier(DataEntryTypeAdapter.BEAN_NAME) DataEntryTypeAdapter dataEntryTypeAdapter,
        @Qualifier(ErrorEntryTypeAdapter.BEAN_NAME) ErrorEntryTypeAdapter errorEntryTypeAdapter
    ) {
        return new DataTypeAdapter(gsonBuilder, dataFactory, dataEntryTypeAdapter, errorEntryTypeAdapter);
    }

    @Bean(name=DataFactory.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TypedFactory<Data> getDataFactory () {
        return new DataFactory();
    }

    @Bean(name=DataEntryFactory.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TypedFactory<DataEntry> getDataEntryFactory () {
        return new DataEntryFactory();
    }

    @Bean(name=ErrorEntryFactory.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TypedFactory<ErrorEntry> getErrorEntryFactory () {
        return new ErrorEntryFactory();
    }

    @Bean(name=DataEntryTypeAdapter.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataEntryTypeAdapter getDataEntryTypeAdapter (
        @Qualifier(DataEntryFactory.BEAN_NAME) TypedFactory<DataEntry> dataEntryFactory
    ) {
        return new DataEntryTypeAdapter (dataEntryFactory);
    }

    @Bean(name=ErrorEntryTypeAdapter.BEAN_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ErrorEntryTypeAdapter getErrorEntryTypeAdapter (
        @Qualifier(ErrorEntryFactory.BEAN_NAME) TypedFactory<ErrorEntry> errorEntryFactory
    ) {
        return new ErrorEntryTypeAdapter (errorEntryFactory);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Data getData () {
        return new Data ();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DataEntry getDataEntry () {
        return new DataEntry ();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ErrorEntry getErrorEntry () {
        return new ErrorEntry ();
    }

    @Bean(name=GSON_BUILDER)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GsonBuilder getGsonBuilder () {
        return new GsonBuilder ();
    }
}
