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
        DogModel mockModel = new DogModel("pluto",5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("http://localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig);
        ApiFactory.setDefaultImplementation(mockImpl);

        ApiInterface defaultImplementation = ApiFactory.getDefaultImplementation();

        // execute
        DogModel savedModel = (DogModel)  defaultImplementation.save(mockModel);

        // assert
        assertEquals("pluto",savedModel.getName());
        assertEquals(3,savedModel.getAge());

    }

    @Test
    public void test_that_its_possible_to_delete_the_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto",5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("http://localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig);
        ApiFactory.setDefaultImplementation(mockImpl);

        ApiInterface defaultImplementation = ApiFactory.getDefaultImplementation();

        // execute
        defaultImplementation.delete(mockModel);



    }


    @Test
    public void test_that_its_possible_to_fetch_an_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto", 5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("http://localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig);
        ApiFactory.setDefaultImplementation(mockImpl);

        ApiInterface defaultImplementation = ApiFactory.getDefaultImplementation();

        // execute
        DogModel savedModel = (DogModel) defaultImplementation.refresh(mockModel);

        // assert
        assertEquals("pluto", savedModel.getName());
        assertEquals(3, savedModel.getAge());


    }

    @Test
    public void test_that_its_possible_to_update_an_model() throws Exception {

        // prepare
        DogModel mockModel = new DogModel("pluto",5);
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("http://localhost").build();
        MockDefaultApiImplementation mockImpl = new MockDefaultApiImplementation(apiConfig);
        ApiFactory.setDefaultImplementation(mockImpl);

        ApiInterface defaultImplementation = ApiFactory.getDefaultImplementation();

        // execute
        DogModel savedModel = (DogModel)  defaultImplementation.update(mockModel);

        // assert
        assertEquals("pluto",savedModel.getName());
        assertEquals(3,savedModel.getAge());


    }

}
