package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Response;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.uri.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ejeserl on 9/19/15.
 */
public class MockDefaultApiImplementation extends DefaultApiImplementation{

    MockDefaultApiImplementation(ApiConfig config) {
        super(config);
    }

    @Override
    Response execute(AsyncHttpClient.BoundRequestBuilder boundRequestBuilder) throws ExecutionException, InterruptedException {

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
                return "{\"name\":\"pluto\",\"age\":3}";
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
