package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Request;
import com.ning.http.client.Response;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.uri.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by ejeserl on 9/19/15.
 */
public class MockDefaultApiImplementation extends DefaultApiImplementation{

    private Request latest;
    private final String dataToEcho;
    private ExecutionException exception;

    MockDefaultApiImplementation(ApiConfig config,String name) {
        super(config);

        this.dataToEcho =  convertStreamToString(this.getClass().getResourceAsStream(name));

    }

    MockDefaultApiImplementation(ApiConfig config,ExecutionException exception) {
        super(config);
        dataToEcho = "";
        this.exception= exception;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    public Request getLatestExecutedRequestBuilder() {
        return latest;
    }
    @Override
    Response execute(AsyncHttpClient.BoundRequestBuilder boundRequestBuilder) throws ExecutionException, InterruptedException {

        if(this.exception != null) {
            throw this.exception;
        }

        this.latest = boundRequestBuilder.build();
        return new Response() {
            @Override
            public int getStatusCode() {
                return 0;
            }

            @Override
            public String getStatusText() {
                return null;
            }

            @Override
            public byte[] getResponseBodyAsBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public ByteBuffer getResponseBodyAsByteBuffer() throws IOException {
                return null;
            }

            @Override
            public InputStream getResponseBodyAsStream() throws IOException {
                return null;
            }

            @Override
            public String getResponseBodyExcerpt(int i, String s) throws IOException {
                return null;
            }

            @Override
            public String getResponseBody(String s) throws IOException {
                return null;
            }

            @Override
            public String getResponseBodyExcerpt(int i) throws IOException {
                return null;
            }

            @Override
            public String getResponseBody() throws IOException {
                return dataToEcho;
            }

            @Override
            public Uri getUri() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public List<String> getHeaders(String s) {
                return null;
            }

            @Override
            public FluentCaseInsensitiveStringsMap getHeaders() {
                return null;
            }

            @Override
            public boolean isRedirected() {
                return false;
            }

            @Override
            public List<Cookie> getCookies() {
                return null;
            }

            @Override
            public boolean hasResponseStatus() {
                return false;
            }

            @Override
            public boolean hasResponseHeaders() {
                return false;
            }

            @Override
            public boolean hasResponseBody() {
                return false;
            }
        };

    }


}
