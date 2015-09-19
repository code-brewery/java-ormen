package org.codebrewery;

/**
 * Created by ejeserl on 9/19/15.
 *
 * This class acts as an factory for retrieving instances.
 *
 * The factory returns objects implementing the APIInterface
 */
public class ApiFactory {

    public static ApiInterface getDefaultImplementation() {
        return new DefaultApiImplementation();
    }


}
