package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class MockRestModel extends AbstractRESTModel{
    @Override
    String resourceUrl() {
        return "dogs";
    }

    @Override
    String identifierValue() {

        return "identifier";

    }

    @Override
    public AbstractRESTModel parse(JSONObject response) throws IOException {

        return new MockRestModel();
    }


}
