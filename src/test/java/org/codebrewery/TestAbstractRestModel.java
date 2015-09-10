package org.codebrewery;

import com.ning.http.client.AsyncCompletionHandler;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.Response;
import com.ning.http.client.listenable.AbstractListenableFuture;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class TestAbstractRestModel {
    private MockRestModel mockModel;
    @Before
    public void before() {
        mockModel = new MockRestModel("plutoTheTester");
    }

    @Test
    public void testThatGeneratingFullHttpUrlCombinesTheBaseUrlWithModelUrl() {

        String fullHttpUrl = mockModel.generateCollectionUrl();

        assertEquals("http://localhost:8081/api/dogs",fullHttpUrl);


    }

    @Test
    public void testThatGeneratingFullHttpUrlForInstanceCombinesColectionUrlWithIdentifierValue() {

        String fullHttpUrl = mockModel.generatedInstanceUrl();

        assertEquals("http://localhost:8081/api/dogs/identifier", fullHttpUrl);

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
    public void testConvertRESTModelToJSON() throws IOException {

        String json = mockModel.convertRESTModelToJSON();

        assertEquals("{\"name\":\"plutoTheTester\"}",json);

    }


    @Test
    public void testThatWrapExecutionInCompletableFutureMethodReturnsValidFuture() throws Exception {


        CompletableFuture<RESTModel> handler = mockModel.wrapExecutionInCompletableFuture(new AbstractListenableFuture<Response>() {
            @Override
            public void done() {

            }

            @Override
            public void abort(Throwable throwable) {

            }

            @Override
            public void touch() {

            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Response get() throws InterruptedException, ExecutionException {
                return new MockResponse();
            }

            @Override
            public Response get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        });

        MockRestModel resp = (MockRestModel) handler.get();

        assertEquals("plutoTheTester", resp.name);

    }


    @Test
    public void testThatFetchReturnsRightUrl() {
        CompletableFuture<RESTModel> future = mockModel.fetch();
        Request request = mockModel.getBoundRequestBuilder().build();

        assertEquals("http://localhost:8081/api/dogs/identifier",request.getUrl());

    }


    @Test
    public void testThatPostReturnsRightUrl() throws IOException {
        CompletableFuture<RESTModel> future = mockModel.create();
        Request request = mockModel.getBoundRequestBuilder().build();

        assertEquals("http://localhost:8081/api/dogs",request.getUrl());

    }

    @Test
    public void testThatDeleteReturnsRightUrl() {
        CompletableFuture<RESTModel> future = mockModel.destroy();
        Request request = mockModel.getBoundRequestBuilder().build();

        assertEquals("http://localhost:8081/api/dogs/identifier",request.getUrl());

    }

    @Test
    public <U> void testThatUpdateReturnsRightUrl() throws IOException {
        CompletableFuture<RESTModel> future = mockModel.update();
        Request request = mockModel.getBoundRequestBuilder().build();
        future.thenApply(result -> isDone(result));      // sync callback

        assertEquals("http://localhost:8081/api/dogs/identifier", request.getUrl());

    }

    private Void isDone(RESTModel result) {
        MockRestModel model = (MockRestModel)result;
        System.out.println(model.name);

        return null;
    }


}
