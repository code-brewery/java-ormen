package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by ejeserl on 9/19/15.
 *
 *
 */
public class DefaultApiImplementation implements ApiInterface {

    private ApiConfig config;
    @Override
    public void markAsDirty(Model model) {

    }

    DefaultApiImplementation() {
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("http://localhost").build();
        this.config = apiConfig;
    }

    DefaultApiImplementation(ApiConfig config) {
        this.config = config;
    }

    public String generateCompleteBaseUrl() {

        return "http://"+config.getHost()  + ":" + config.getPort() + "/" + config.getApiLocation();
    }
    @Override
    public Model save(Model model) throws JavaOrmenException {
        try {

            String url = generateCompleteBaseUrl() + generateCollectionUrl(model.getClass());
            // execute the query
            Response response = execute(new AsyncHttpClient().prepareGet(url));
            // convert the response to an Model
            return JSONConverter.unMarshall(response.getResponseBody(), model.getClass());

        } catch (InterruptedException  | ExecutionException | IOException e) {
            // wrap the exception in a javaOrmenException
           throw new JavaOrmenException("failed to save model with identifier" + model.getIdentifierValue(),e);
        }
    }

    // wrapped in a method so that we can test the requests
    Response execute(AsyncHttpClient.BoundRequestBuilder boundRequestBuilder) throws ExecutionException, InterruptedException {

        return boundRequestBuilder.execute().get();

    }

    private String convertRESTModelToJSON() {
        return null;
    }

    private String generateCollectionUrl(Class<? extends Model> aClass) {

        return ModelHelper.getCollectionUrlLocation(aClass);

    }

    @Override
    public Model update(Model model) throws JavaOrmenException {
        try {

            String url = generateCompleteBaseUrl() + generateCollectionUrl(model.getClass());
            // execute the query
            Response response = execute(new AsyncHttpClient().preparePut(url).setBody(JSONConverter.marshall(model)));
            // convert the response to an Model
            return JSONConverter.unMarshall(response.getResponseBody(), model.getClass());

        } catch (InterruptedException  | ExecutionException | IOException e) {
            // wrap the exception in a javaOrmenException
            throw new JavaOrmenException("failed to save model with identifier " + model.getIdentifierValue(),e);
        }
    }

    @Override
    public Model insert(Model model) throws JavaOrmenException {

            try {

                String url = generateCompleteBaseUrl() + generateCollectionUrl(model.getClass());
                // execute the query
                Response response = execute(new AsyncHttpClient().preparePost(url).setBody(JSONConverter.marshall(model)));
                // convert the response to an Model
                return JSONConverter.unMarshall(response.getResponseBody(), model.getClass());

            } catch (InterruptedException  | ExecutionException | IOException e) {
                // wrap the exception in a javaOrmenException
                throw new JavaOrmenException("failed to save model with identifier " + model.getIdentifierValue(),e);
            }
    }

    @Override
    public void delete(Model model) throws JavaOrmenException {
        try {

            String url = generateCompleteBaseUrl() + generateCollectionUrl(model.getClass());
            // execute the query
            Response response = execute(new AsyncHttpClient().prepareDelete(url));
            // convert the response to an Model

        } catch (InterruptedException  | ExecutionException e) {
            // wrap the exception in a javaOrmenException
            throw new JavaOrmenException("failed to save model with identifier " + model.getIdentifierValue(),e);
        }
    }

    @Override
    public Model refresh(Model model) throws JavaOrmenException {
        try {

            String url = generateCompleteBaseUrl() + generateCollectionUrl(model.getClass());
            // execute the query
            Response response = execute(new AsyncHttpClient().prepareGet(url).setBody(JSONConverter.marshall(model)));
            // convert the response to an Model
            return JSONConverter.unMarshall(response.getResponseBody(), model.getClass());

        } catch (InterruptedException  | ExecutionException | IOException e) {
            // wrap the exception in a javaOrmenException
            throw new JavaOrmenException("failed to save model with identifier " + model.getIdentifierValue(),e);
        }
    }
}
