package com.coherentlogic.coherent.data.adapter.openfigi.core.extractors;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.Data;

/**
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class DataExtractor implements ResponseExtractor {

    @Override
    public Object extractData(ClientHttpResponse response) throws IOException {
        return new Data ();
    }
}
