package org.codebrewery;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Created by jepp3 on 2015-09-04.
 *
 * This class implements functionality to communicate crud operations through an REST API.
 *
 *
 * The resource should have the body definition for POST,GET,SET operations. Etc its not allowed to have different
 * schemas for different operations. The payload stays the same
 *
 *
 *
 */
public abstract class RESTModel implements CrudModelInterface {

    private RestModelConfiguration config = new RestModelConfiguration();

    /**
     * This method returns the base url for a resource.
     *
     *
     *  /rest/dogs/
     *
     * @return resource url common for all instances of this resource
     */
    abstract String resourceUrl();

    /**
     * Returns the uniq identifierValue value of the resource. This might in  many cases be
     * the value of the id private variable.
     *
     *
     *  "123"
     *
     * @return
     */
    abstract  String identifierValue();




    protected String generateCollectionUrl() {

        return config.getRequestBaseUrl() + "/" + resourceUrl();

    }

    protected String generatedInstanceUrl() {

        return generateCollectionUrl() + "/" + identifierValue();

    }

    public void setConfiguration( RestModelConfiguration config ) {

        this.config = config;

    }

    /**
     * This method is used by the RESTModel when converting Responses to RestModels
     *
     * It will use the JSONConverter, that will use a ObjectMapper to resolve the different attribute names
     *
     * @param response
     * @return
     * @throws IOException
     * @throws ParseException
     */
    protected RESTModel convertJSONToRESTModelObject(String json) throws IOException, ParseException {

        return JSONConverter.unMarshall(json, this.getClass());
    }

    /**
     * This method is used by the RESTModel when converting RestModels to json.
     *
     * It will use the JSONConverter, that will use a ObjectMapper to resolve the different attribute names
     *
     * @return
     * @throws IOException
     * @throws ParseException
     */
    protected String convertRESTModelToJSON() throws IOException {

        return JSONConverter.marshall(this);

    }

    protected AsyncCompletionHandler getAsyncCompletionHandler(ActionCompletedInterface actions) {

        return new AsyncCompletionHandler(){

            @Override
            public Object onCompleted(Response response) throws Exception {


                actions.onDone(convertJSONToRESTModelObject(response.getResponseBody()));

                return null;
            }

            @Override
            public void onThrowable(Throwable t) {

                actions.onError(t);

            }
        };

    }


    public void fetch(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {

        new AsyncHttpClient().prepareGet(generatedInstanceUrl()).execute(getAsyncCompletionHandler(actions));

    }

    public void destroy(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {

        new AsyncHttpClient().prepareDelete(generatedInstanceUrl()).execute(getAsyncCompletionHandler(actions));

    }

    public void update(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException, IOException {

        new AsyncHttpClient().preparePut(generatedInstanceUrl()).setBody(convertRESTModelToJSON()).execute(getAsyncCompletionHandler(actions));

    }


    public void create(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException, IOException {

        new AsyncHttpClient().preparePost(generateCollectionUrl()).setBody(convertRESTModelToJSON()).execute(getAsyncCompletionHandler(actions));

    }




}
