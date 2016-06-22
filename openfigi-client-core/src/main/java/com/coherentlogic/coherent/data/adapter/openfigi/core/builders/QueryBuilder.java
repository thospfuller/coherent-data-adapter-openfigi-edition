package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.RequestBodyAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.MappingEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.model.core.adapters.InReturnAdapterSpecification;
import com.coherentlogic.coherent.data.model.core.builders.rest.AbstractRESTQueryBuilder;
import com.coherentlogic.coherent.data.model.core.cache.CacheServiceProviderSpecification;

/**
 * 
 * OPEN_FIGI_API_KEY
 *
 * @see <a href="https://www.openfigi.com/api">The OpenFIGI API</a>
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 *
 */
public class QueryBuilder extends AbstractRESTQueryBuilder {

    public static final String DEFAULT_URI = "https://api.openfigi.com/v1/mapping";

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    private final RequestBody requestBody;

    private MappingEntry currentMappingEntry;

    private final Map<String, String> headers;

    private final InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter = new RequestBodyAdapter ();

    private final ResponseExtractor<Data> dataExtractor;

    @Autowired
    private ApplicationContext applicationContext;

    public QueryBuilder(RestTemplate restTemplate, ResponseExtractor<Data> dataExtractor) {
        this(restTemplate, DEFAULT_URI, dataExtractor);
    }

    public QueryBuilder(RestTemplate restTemplate, String uri, ResponseExtractor<Data> dataExtractor) {
        super(restTemplate, uri);
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri);
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(RestTemplate restTemplate, UriBuilder uriBuilder, ResponseExtractor<Data> dataExtractor) {
        super(restTemplate, uriBuilder);
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder);
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        CacheServiceProviderSpecification<String, Object> cache,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri, cache);
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        CacheServiceProviderSpecification<String, Object> cache,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri, cache);
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        CacheServiceProviderSpecification<String, Object> cache,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder, cache);
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        CacheServiceProviderSpecification<String, Object> cache,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder, cache);
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public QueryBuilder withApiKey (String apiKey) {

        headers.put("X-OPENFIGI-APIKEY", apiKey);

        return this;
    }

//    /**
//     * Method constructs the URI and first checks to see if the object currently
//     * exists in the cache -- if it does, then this object is returned, other-
//     * -wise the URI is called and the resultant XML is converted into an
//     * instance of type <i>type</i> and the result is returned to the user. 
//     */
//    public <T> T doPost(Class<T> type, RequestBody requestBody) {
//
//        String escapedURI = getEscapedURI();
//
//        T result = null;
//
//        CacheServiceProviderSpecification<String, Object> cache = getCache();
//
//        Object object = cache.get(escapedURI);
//
//        if (object != null && type.isInstance(object))
//            result = (T) object;
//        else if (object != null && !type.isInstance(object))
//            throw new ClassCastException (
//                "The object " + object +
//                " cannot be cast to type " + type + ".");
//        else if (object == null) {
//            result = (T) getRestTemplate().execute(
//                escapedURI,
//                HttpMethod.POST,
//                new RequestCallback() {
//
//                    @Override
//                    public void doWithRequest(ClientHttpRequest request) throws IOException {
//                        request.getHeaders().add("X-OPENFIGI-APIKEY", "16597a6c-3abb-4627-b91c-c808666b790c");
//                        request.getHeaders().add("Content-Type", "application/json");
//
//                        for (String header : headers.keySet()) {
//                            request.getHeaders().add(header, headers.get(header));
//                        }
//
//                        String requestBodyJson = requestBodyAdapter.adapt(getRequestBody());
//
//                        log.info("requestBodyJson: " + requestBodyJson);
//
//                        // "[{\"idType\":\"ID_WERTPAPIER\",\"idValue\":\"851399\"}]"
//                        
//                        request.getBody().write(requestBodyJson.getBytes());
//                    }
//                },
//                applicationContext.getBean(DataExtractor.class)
//            );//postForObject(escapedURI, requestBody, type););//exchange(escapedURI, HttpMethod.POST, entity, type);//postForObject(escapedURI, requestBody, type);
//            cache.put(escapedURI, result);
//        }
//        return result;
//    }

    @Override
    protected <T> T doExecute(Class<T> type) {

        T result = (T) getRestTemplate().execute(
            getEscapedURI(),
            HttpMethod.POST,
            new RequestCallback() {

                @Override
                public void doWithRequest(ClientHttpRequest request) throws IOException {

                    HttpHeaders headers = request.getHeaders();

                    headers.setContentType(MediaType.APPLICATION_JSON);

                    for (Entry<String, String> nextEntry : getHeaders ().entrySet()) {

                        String key = nextEntry.getKey();
                        String value = nextEntry.getValue();

                        headers.add(key, value);
                    }

                    String requestBodyJson = requestBodyAdapter.adapt(getRequestBody());

                    log.info("requestBodyJson: " + requestBodyJson);

                    request.getBody().write(requestBodyJson.getBytes());
                }
            },
            dataExtractor
        );

        return result;
    }

    public Data doGet () {
        return doGet (Data.class);
    }

// DO NOT DELETE -- THIS WORKS AND NEEDS TO GO INTO THE doPost method.
//    HttpEntity<RequestBody> entity = new HttpEntity<RequestBody> (new RequestBody (this));
//
//    entity.getHeaders().set(headerName, headerValue);
//
//    getRestTemplate().exchange(getEscapedURI(), HttpMethod.POST, entity, Data.class);
}
