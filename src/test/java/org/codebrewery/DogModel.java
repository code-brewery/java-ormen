package org.codebrewery;


import org.json.simple.JSONObject;


/**
 * Created by jepp3 on 2015-09-04.
 */
public class DogModel extends AbstractRESTModel {

    // private vars that shall map against the json data fetched

    public final String name;

    public final int age;


    public DogModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected String resourceUrl() {

        return "/rest/dogs/";

    }

    @Override
    protected String identifierValue() {

        return this.name;
    }

    @Override
    public final AbstractRESTModel  parse(JSONObject responseJson) {
        String name = (String) responseJson.get("name");

        return new DogModel(name,123);

    }

    // dedicated method
    public String bark() {
        return "VOFF says" + this.name;
    }



}
