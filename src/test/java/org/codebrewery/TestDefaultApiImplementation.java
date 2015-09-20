package org.codebrewery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ejeserl on 9/19/15.
 */
public class TestDefaultApiImplementation {

    @Test
    public void test_that_its_possible_to_save_an_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto", 5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");

        // execute
        DogModel savedModel = (DogModel) mockImpl.save(mockModel);

        // assert
        assertEquals("pluto", savedModel.getName());
        assertEquals(3, savedModel.getAge());
        assertEquals("http://localhost:8081/api/dogs", mockImpl.getLatestExecutedRequestBuilder().getUrl());
    }

    @Test
    public void test_that_its_possible_to_delete_the_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto",5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");


        // execute
        mockImpl.delete(mockModel);

        assertEquals("http://localhost:8081/api/dogs/pluto", mockImpl.getLatestExecutedRequestBuilder().getUrl());


    }


    @Test
    public void test_that_its_possible_to_fetch_an_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto", 5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");


        // execute
        DogModel savedModel = (DogModel) mockImpl.fetch(mockModel);

        // assert
        assertEquals("pluto", savedModel.getName());
        assertEquals(3, savedModel.getAge());
        assertEquals("http://localhost:8081/api/dogs/pluto", mockImpl.getLatestExecutedRequestBuilder().getUrl());

    }

    @Test
    public void test_that_its_possible_to_update_an_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto",5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");

        // execute
        DogModel savedModel = (DogModel)  mockImpl.update(mockModel);

        // assert
        assertEquals("pluto",savedModel.getName());
        assertEquals(3,savedModel.getAge());
        assertEquals("http://localhost:8081/api/dogs/pluto", mockImpl.getLatestExecutedRequestBuilder().getUrl());

    }

}
