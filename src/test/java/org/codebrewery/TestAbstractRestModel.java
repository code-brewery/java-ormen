package org.codebrewery;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Response;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.uri.Uri;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class TestAbstractRestModel {
    private MockRestModel mockModel;
    @Before
    public void before() {
        mockModel = new MockRestModel();
    }

    @Test
    public void testThatGeneratingFullHttpUrlCombinesTheBaseUrlWithModelUrl() {

        String fullHttpUrl = mockModel.generateCollectionUrl();

        assertEquals("http://localhost:8081/api/dogs",fullHttpUrl);


    }

    @Test
    public void testThatGeneratingFullHttpUrlForInstanceCombinesColectionUrlWithIdentifierValue() {

        String fullHttpUrl = mockModel.generatedInstanceUrl();

        assertEquals("http://localhost:8081/api/dogs/identifier",fullHttpUrl);

    }


    @Test
    public void testThatSettingANewConfigWorks() {
        mockModel.setConfiguration(new RestModelConfiguration(){
            @Override
            public String getBaseUrl() {
                return "http://baseUrlChanged";
            }
        });
        String fullHttpUrl = mockModel.generatedInstanceUrl();

        assertEquals("http://baseUrlChanged:8081/api/dogs/identifier", fullHttpUrl);

    }


    @Test
    public void testThatAsyncCompletionHandlerDoesWhatItsSuposedTo() throws Exception {

        ActionCompletedInterface mockAction = new ActionCompletedInterface() {

            @Override
            public void onDone(AbstractRESTModel model) {

                    assertNotNull(model);
            }

            @Override
            public void onError(Throwable e) {

                fail();
            }
        };

        AsyncCompletionHandler handler = mockModel.getAsyncCompletionHandler(mockAction);

        // invoke the handler directly
        handler.onCompleted(new MockResponse());


    }


    @Test
    public void testThatAsyncCompletionHandleronERRORDoesWhatItsSuposedTo() throws Exception {

        ActionCompletedInterface mockAction = new ActionCompletedInterface() {

            @Override
            public void onDone(AbstractRESTModel model) {

                fail();
            }

            @Override
            public void onError(Throwable e) {

                assertNotNull(e);

            }
        };

        AsyncCompletionHandler handler = mockModel.getAsyncCompletionHandler(mockAction);

        // invoke the handler directly
        handler.onThrowable(new Exception());


    }

    @Test
    public void testConvertResponseToJSONObject() throws IOException, ParseException {

        Response mockResponse = new MockResponse();

        JSONObject jsonObject = mockModel.convertResponseToJSONObject(mockResponse);

        assertTrue(jsonObject.containsKey("name"));
    }
}
