package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by jepp3 on 2015-09-06.
 */
@JsonIgnoreProperties({"boundRequestBuilder"})
public class MockRestModel extends RESTModel {

    public final String name;
    private AsyncHttpClient.BoundRequestBuilder boundRequestBuilder;

    public MockRestModel() {
        this.name = "";
    }

    public MockRestModel(String name) {
        this.name = name;
    }

    @Override
    String resourceUrl() {
        return "dogs";
    }

    @Override
    String identifierValue() {

        return "identifier";

    }

    @Override
    ListenableFuture<Response> execute(AsyncHttpClient.BoundRequestBuilder boundRequestBuilder) {

        this.boundRequestBuilder = boundRequestBuilder;
        return null;

    }
    // for testing
    public AsyncHttpClient.BoundRequestBuilder getBoundRequestBuilder() {
        return boundRequestBuilder;
    }
}
