package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
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




    String generateCollectionUrl() {

        return config.getRequestBaseUrl() + "/" + resourceUrl();

    }

    String generatedInstanceUrl() {

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
     * @return
     * @throws IOException
     * @throws ParseException
     */
    RESTModel convertJSONToRESTModelObject(String json) throws IOException, ParseException {

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
    String convertRESTModelToJSON() throws IOException {

        return JSONConverter.marshall(this);

    }

    CompletableFuture<RESTModel> wrapExecutionInCompletableFuture(ListenableFuture<Response> futureResponse) {

        return CompletableFuture.supplyAsync(() -> {

            try {
                return convertJSONToRESTModelObject(futureResponse.get().getResponseBody());
            } catch (IOException | ParseException | InterruptedException | ExecutionException e4) {

                throw new CompletionException(e4);

            }


        });
    }


    ListenableFuture<Response> execute(AsyncHttpClient.BoundRequestBuilder boundRequestBuilder) {

        return boundRequestBuilder.execute();

    }

    public CompletableFuture<RESTModel> fetch()  {

        return wrapExecutionInCompletableFuture(execute(new AsyncHttpClient().prepareGet(generatedInstanceUrl())));

    }

    public CompletableFuture<RESTModel> destroy() {

        return wrapExecutionInCompletableFuture(execute(new AsyncHttpClient().prepareDelete(generatedInstanceUrl())));

    }

   public CompletableFuture<RESTModel> update() throws IOException {

        return wrapExecutionInCompletableFuture(execute(new AsyncHttpClient().preparePut(generatedInstanceUrl()).setBody(convertRESTModelToJSON())));

    }


    public CompletableFuture<RESTModel> create() throws IOException {

        return wrapExecutionInCompletableFuture(execute(new AsyncHttpClient().preparePost(generateCollectionUrl()).setBody(convertRESTModelToJSON())));

    }




}
