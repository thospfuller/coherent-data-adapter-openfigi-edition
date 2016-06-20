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
 * @author <a href="support@coherentlogic.com">Support</a>
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

        throw new RuntimeException ("write: method failed!");

//        RequestBody requestBody = (RequestBody) object;

//        Gson gson = new GsonBuilder().create();
//        
//        gson.
//        
//        outputMessage.getBody().write(bytes);
//        
    }
}

//public RequestBodyConverter() {
//    // TODO Auto-generated constructor stub
//}
//
//@Override
//public boolean canConvert(Class type) {
//    return RequestBody.class.equals(type);
//}
//
//@Override
//public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
//
//    RequestBody requestBody = (RequestBody) source;
//
//    writer.startNode("");
//    //writer.addAttribute("foo", "bar");
//
//    for (MappingEntry mappingEntry : requestBody.getMappingEntries()) {
//        context.convertAnother(mappingEntry);
//    }
//
//    writer.endNode();
//}
//
//@Override
//public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
//    // TODO Auto-generated method stub
//    return null;
//}
//
//public static void main (String[] unused) {
//
//    Converter converter = new RequestBodyConverter ();
//
//    StringWriter writer = new StringWriter ();
//
//    JsonWriter jsonWriter = new JsonWriter(writer);
//
//    converter.marshal(
//        new RequestBody(null),
//        jsonWriter,
//        new MarshallingContext () {
//
//            @Override
//            public Object get(Object key) {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public void put(Object key, Object value) {
//                // TODO Auto-generated method stub
//                
//            }
//
//            @Override
//            public Iterator keys() {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public void convertAnother(Object nextItem) {
//                // TODO Auto-generated method stub
//                
//            }
//
//            @Override
//            public void convertAnother(Object nextItem, Converter converter) {
//                // TODO Auto-generated method stub
//                
//            }
//        }
//    );
//    
//    jsonWriter.flush();
//    jsonWriter.close();
//
//    System.out.println("result: " + writer.getBuffer().toString());
//}