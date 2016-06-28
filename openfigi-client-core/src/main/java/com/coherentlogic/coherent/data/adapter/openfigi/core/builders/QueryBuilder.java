package com.coherentlogic.coherent.data.adapter.openfigi.core.builders;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.openfigi.core.adapters.RequestBodyAdapter;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;
import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;
import com.coherentlogic.coherent.data.model.core.adapters.InReturnAdapterSpecification;
import com.coherentlogic.coherent.data.model.core.builders.rest.AbstractRESTQueryBuilder;
import com.coherentlogic.coherent.data.model.core.cache.CacheServiceProviderSpecification;

/**
 * Builder for querying the OpenFIGI.com web services -- for example:
 * <pre>
 * Data data = queryBuilder
 *     .withApiKey(API_KEY)
 *     .getRequestBody()
 *         .withIsin("US4592001014")
 *         .withWertpapier("851399")
 *     .done()
 * .doGet();
 * </pre>
 * Below is an equivalent example to the one above, but with the IdType and IdValue set directly on the mapping entry:
 * <pre>
 * Data data = queryBuilder
 *     .withApiKey(API_KEY)
 *     .getRequestBody()
 *         .newMappingEntry()
 *             .withIdType("ID_ISIN")
 *             .withIdValue("US4592001014")
 *         .done()
 *         .newMappingEntry()
 *             .withIdType("ID_WERTPAPIER")
 *             .withIdValue("851399")
 *         .done()
 *     .done()
 * .doGet();
 * </pre>
 *
 * @see <a href="https://www.openfigi.com/api">The OpenFIGI API</a>
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 *
 */
public class QueryBuilder extends AbstractRESTQueryBuilder<RequestKey> {

    public static final String DEFAULT_URI = "https://api.openfigi.com/v1/mapping",
        X_OPENFIGI_APIKEY = "X-OPENFIGI-APIKEY";

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    private final RequestBody requestBody;

    private final Map<String, String> headers;

    private final InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter;

    private final ResponseExtractor<Data> dataExtractor;

    public QueryBuilder(RestTemplate restTemplate, ResponseExtractor<Data> dataExtractor) {
        this(restTemplate, DEFAULT_URI, new RequestBodyAdapter (), dataExtractor);
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        CacheServiceProviderSpecification<RequestKey, Object> cache,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri, cache);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        CacheServiceProviderSpecification<RequestKey, Object> cache,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uri, cache);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = requestBody;
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        CacheServiceProviderSpecification<RequestKey, Object> cache,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder, cache);
        this.requestBodyAdapter = requestBodyAdapter;
        this.requestBody = new RequestBody (this);
        headers = new HashMap<String, String> ();
        this.dataExtractor = dataExtractor;
    }

    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        CacheServiceProviderSpecification<RequestKey, Object> cache,
        InReturnAdapterSpecification<RequestBody, String> requestBodyAdapter,
        RequestBody requestBody,
        ResponseExtractor<Data> dataExtractor
    ) {
        super(restTemplate, uriBuilder, cache);
        this.requestBodyAdapter = requestBodyAdapter;
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

        headers.put(X_OPENFIGI_APIKEY, apiKey);

        return this;
    }

    @Override
	protected RequestKey getKey() {
		return new RequestKey (headers, requestBody);
	}

	@Override
    protected <T> T doExecute(Class<T> type) {

        T result = (T) getCache().get(getKey ());

        if (result == null) {
            result = (T) getRestTemplate().execute(
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

                        log.debug("headers: " + headers + ", requestBodyJson: " + requestBodyJson);

                        request.getBody().write(requestBodyJson.getBytes());
                    }
                },
                dataExtractor
            );
        }

        return result;
    }

    public Data doGet () {
        return doGet (Data.class);
    }
}

class RequestKey implements Serializable {

    private static final long serialVersionUID = 3061141466398240807L;

    private final Map<String, String> headers;

    private final RequestBody requestBody;

    public RequestKey(Map<String, String> headers, RequestBody requestBody) {
        this.headers = headers;
        this.requestBody = requestBody;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
        result = prime * result + ((requestBody == null) ? 0 : requestBody.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestKey other = (RequestKey) obj;
        if (headers == null) {
            if (other.headers != null)
                return false;
        } else if (!headers.equals(other.headers))
            return false;
        if (requestBody == null) {
            if (other.requestBody != null)
                return false;
        } else if (!requestBody.equals(other.requestBody))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Request [headers=" + headers + ", requestBody=" + requestBody + "]";
    }
}