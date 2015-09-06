package org.codebrewery;

/**
 * Created by jepp3 on 2015-09-06.
 */
public class RestModelConfiguration {


    public String getRequestBaseUrl(){

        return getBaseUrl() + ":" + getPort() + "/" + getApiLocation();

    }

    public String getBaseUrl() {

        return "http://localhost";

    }

    public String getPort() {

        return "8081";

    }

    public String getApiLocation() {

        return "api";

    }
}
