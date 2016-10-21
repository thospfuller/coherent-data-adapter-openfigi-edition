package com.coherentlogic.coherent.data.adapter.openfigi.core.converters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.coherentlogic.coherent.data.adapter.openfigi.core.domain.RequestBody;

/**
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class RequestBodyConverter implements HttpMessageConverter {

    @Override
    public boolean canRead(Class arg0, MediaType arg1) {
        throw new UnsupportedOperationException("[canRead] The RequestBody class will never be read.");
    }

    @Override
    public boolean canWrite(Class target, MediaType mediaType) {
        return RequestBody.class.equals(target)
            && (MediaType.APPLICATION_JSON.equals(mediaType) || MediaType.APPLICATION_JSON_UTF8.equals(mediaType));
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public Object read(Class target, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("[read] The RequestBody class will never be read.");
    }

    @Override
    public void write(Object object, MediaType mediaType, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException("[write] The RequestBody class will never be written.");
    }
}
