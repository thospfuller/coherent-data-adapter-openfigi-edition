package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import javax.ws.rs.core.UriBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.MappingEntry;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.model.core.builders.HTTPPostMethodSpecification;
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
public class QueryBuilder extends AbstractRESTQueryBuilder implements HTTPPostMethodSpecification {

    public static final String DEFAULT_URI = "https://api.openfigi.com/v1/mapping";

    private final RequestBody requestBody;

    private MappingEntry currentMappingEntry;

    private HttpHeaders headers;

    private final HttpEntity<RequestBody> entity;

    public QueryBuilder(RestTemplate restTemplate) {
        this(restTemplate, DEFAULT_URI);
    }

    public QueryBuilder(RestTemplate restTemplate, String uri) {
        super(restTemplate, uri);
        this.requestBody = new RequestBody (this);
        headers = new HttpHeaders ();
        entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, String uri, RequestBody requestBody) {
        super(restTemplate, uri);
        this.requestBody = requestBody;
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, UriBuilder uriBuilder) {
        super(restTemplate, uriBuilder);
        this.requestBody = new RequestBody (this);
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, UriBuilder uriBuilder, RequestBody requestBody) {
        super(restTemplate, uriBuilder);
        this.requestBody = requestBody;
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, String uri,
        CacheServiceProviderSpecification<String, Object> cache) {
        super(restTemplate, uri, cache);
        this.requestBody = new RequestBody (this);
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, String uri,
        CacheServiceProviderSpecification<String, Object> cache, RequestBody requestBody) {
        super(restTemplate, uri, cache);
        this.requestBody = requestBody;
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(RestTemplate restTemplate, UriBuilder uriBuilder,
        CacheServiceProviderSpecification<String, Object> cache) {
        super(restTemplate, uriBuilder, cache);
        this.requestBody = new RequestBody (this);
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public QueryBuilder(
    	RestTemplate restTemplate,
    	UriBuilder uriBuilder,
        CacheServiceProviderSpecification<String, Object> cache,
        RequestBody requestBody
    ) {
        super(restTemplate, uriBuilder, cache);
        this.requestBody = requestBody;
        headers = new HttpHeaders ();
        this.entity = new HttpEntity<RequestBody> (requestBody, headers);
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public QueryBuilder withApiKey (String apiKey) {

        headers.set("X-OPENFIGI-APIKEY", apiKey);

        return this;
    }

    public QueryBuilder withContentType (MediaType mediaType) {

        headers.setContentType(mediaType);

        return this;
    }

    public QueryBuilder withContentTypeAsApplicationJson () {
        return withContentType (MediaType.APPLICATION_JSON);
    }

    /**
     * Method constructs the URI and first checks to see if the object currently
     * exists in the cache -- if it does, then this object is returned, other-
     * -wise the URI is called and the resultant XML is converted into an
     * instance of type <i>type</i> and the result is returned to the user. 
     */
    @Override
    public <T> T doPost(Class<T> type) {
        return doPost (type, getRequestBody());
    }

    /**
     * Method constructs the URI and first checks to see if the object currently
     * exists in the cache -- if it does, then this object is returned, other-
     * -wise the URI is called and the resultant XML is converted into an
     * instance of type <i>type</i> and the result is returned to the user. 
     */
    public <T> T doPost(Class<T> type, RequestBody requestBody) {

        String escapedURI = getEscapedURI();

        T result = null;

        CacheServiceProviderSpecification<String, Object> cache = getCache();

        Object object = cache.get(escapedURI);

        if (object != null && type.isInstance(object))
            result = (T) object;
        else if (object != null && !type.isInstance(object))
            throw new ClassCastException (
                "The object " + object +
                " cannot be cast to type " + type + ".");
        else if (object == null) {
            result = (T) getRestTemplate().exchange(escapedURI, HttpMethod.POST, entity, type);//postForObject(escapedURI, requestBody, type);
            cache.put(escapedURI, result);
        }
        return result;
    }

// DO NOT DELETE -- THIS WORKS AND NEEDS TO GO INTO THE doPost method.
//    HttpEntity<RequestBody> entity = new HttpEntity<RequestBody> (new RequestBody (this));
//
//    entity.getHeaders().set(headerName, headerValue);
//
//    getRestTemplate().exchange(getEscapedURI(), HttpMethod.POST, entity, Data.class);
}
