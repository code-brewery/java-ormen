package org.codebrewery;

/**
 * Created by ejeserl on 9/19/15.
 *
 * This class acts as an factory for retrieving instances.
 *
 * The factory returns objects implementing the APIInterface
 */
public class ApiFactory {
    private static ApiInterface defaultServer = new DefaultApiImplementation();

    public static ApiInterface getServer(String server) {
        return null;
    }

    public static ApiInterface getDefaultImplementation() {
        return defaultServer;
    }

    /**
     * This method is only for mocking, hence package scope
     * @param apiImpl
     */
    public static void setDefaultImplementation(final ApiInterface apiImpl) {
        defaultServer = apiImpl;
    }
}
