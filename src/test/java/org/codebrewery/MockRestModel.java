package org.codebrewery;

import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class MockRestModel extends RESTModel {

    public final String name;

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


}
