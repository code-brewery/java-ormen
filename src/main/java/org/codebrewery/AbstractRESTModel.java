package org.codebrewery;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
public abstract class AbstractRESTModel implements CrudModelInterface {

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

    /**
     * Override this method when you want to convert the json data to JAVA Objects
     *
     * @param response
     * @return
     */
    public abstract  AbstractRESTModel  parse(JSONObject response) throws IOException;


    private String generateFullHttpUrl() {

        return "http://localhost:8081"+resourceUrl();

    }

    private JSONObject convertResponseToJSONObject(Response response) throws IOException, ParseException {

        return (JSONObject)new JSONParser().parse(response.getResponseBody());

    }

    public void fetch(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        String urlToUse = generateFullHttpUrl() + identifierValue();
        asyncHttpClient.prepareGet(urlToUse).execute(new AsyncCompletionHandler() {

            @Override
            public Object onCompleted(Response response) throws Exception {


                actions.onDone(parse(convertResponseToJSONObject(response)));

                // is it possible to not return void?
                return null;
            }

            @Override
            public void onThrowable(Throwable t) {

                actions.onError(t);

            }
        });

    }

    public void destroy(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {




    }

    public void update(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {



    }

    public void create(final ActionCompletedInterface actions) throws ExecutionException, InterruptedException {



    }




}
