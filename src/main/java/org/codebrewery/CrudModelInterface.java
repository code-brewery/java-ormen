package org.codebrewery;

import com.ning.http.client.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by jepp3 on 2015-09-04.
 *
 *
 *
 * The crud interface is intended for classes that can be handled like a single resources.
 *
 *
 * One use case is a rest api , where you can access different resources with help of
 * different http verbs mixed with uri:s.
 *
 *
 *
 */
public interface CrudModelInterface {

    /**
     * Fetches a single instance of the resource.
     *
     * The url will be constructed the following way baseurl + identifierValue
     */
    CompletableFuture<RESTModel> fetch();

    /**
     * Destroys the single instance of the model
     *
     * The url will be constructed the following way baseurl + identifierValue
     */
    CompletableFuture<RESTModel> destroy();

    /**
     * updates the resource
     *
     * The url will be constructed the following way baseurl + identifierValue
     */
    CompletableFuture<RESTModel> update() throws IOException;

    /**
     * Creates a new resource
     *
     * The url will only consist o fthe base url
     */
    CompletableFuture<RESTModel> create() throws IOException;
}
