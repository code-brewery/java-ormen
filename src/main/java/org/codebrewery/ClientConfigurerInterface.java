package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;

/**
 * Created by jepp3 on 2015-09-05.
 */
public interface ClientConfigurerInterface {
    /**
     * Implement this method to configure the AbstractRESTModel.
     *
     * The asyncHttpClient uses the Builder pattern and can be configured in a way described by
     * the Async-http-client documentation
     *
     * @return client to be used by AbstractRESTModel
     */
    AsyncHttpClient getConfiguredClient();

}
