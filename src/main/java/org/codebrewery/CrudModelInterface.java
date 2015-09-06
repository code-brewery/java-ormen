package org.codebrewery;

import com.ning.http.client.ListenableFuture;

import java.io.IOException;
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
    void fetch(ActionCompletedInterface actionCompletedInterface) throws ExecutionException, InterruptedException;

    /**
     * Destroys the single instance of the model
     *
     * The url will be constructed the following way baseurl + identifierValue
     */
    void destroy(ActionCompletedInterface actionCompletedInterface) throws ExecutionException, InterruptedException;

    /**
     * updates the resource
     *
     * The url will be constructed the following way baseurl + identifierValue
     */
    void update(ActionCompletedInterface actionCompletedInterface) throws ExecutionException, InterruptedException, IOException;

    /**
     * Creates a new resource
     *
     * The url will only consist o fthe base url
     */
    void create(ActionCompletedInterface actionCompletedInterface) throws ExecutionException, InterruptedException, IOException;
}
