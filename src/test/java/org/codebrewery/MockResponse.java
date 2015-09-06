package org.codebrewery;

import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Response;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.uri.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class MockResponse implements Response {

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
        return "{\"name\":\"pluto\"}";
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
    public String toString() {
        return null;
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

}
