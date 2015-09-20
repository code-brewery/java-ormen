package org.codebrewery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.easymock.EasyMock.expect;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.*;



/**
 * Created by ejeserl on 9/20/15.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiFactory.class)
public class TestModel {


    private ApiConfig apiConfig;
    private MockDefaultApiImplementation mockApiImpl;
    /**
     * All these tests will use powermock.
     *
     */
    public void setUpWithMock() {

        setUpWithMock("plainPluto.json");

    }
    public void setUpWithMock(String fileName) {
        // 1. create config
        // 2. create mock
        // 3. init mock

        apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        mockApiImpl = new MockDefaultApiImplementation(apiConfig,fileName);
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(mockApiImpl);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

    }



    @Test
    public void save_model() throws JavaOrmenException {
        setUpWithMock();
        Model model = new DogModel();

        DogModel  changedModel  = (DogModel) model.save();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();
        // Assert that the ID is correct
        assertEquals("pluto", changedModel.getName());
        assertEquals("http://localhost:8081/api/dogs",mockApiImpl.getLatestExecutedRequestBuilder().getUrl());

    }



    @Test
    public void delete_model() throws JavaOrmenException {
        setUpWithMock();
        Model model = new DogModel("pluto",123);

        model.delete();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals("http://localhost:8081/api/dogs/pluto",mockApiImpl.getLatestExecutedRequestBuilder().getUrl());
    }



    @Test
    public void fetch_model() throws JavaOrmenException {
        setUpWithMock();
        DogModel model = new DogModel("plutoXII",4);

        DogModel  changedModel  = (DogModel) model.fetch();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals("pluto", changedModel.getName());
        assertEquals("http://localhost:8081/api/dogs/plutoXII",mockApiImpl.getLatestExecutedRequestBuilder().getUrl());

        // verify that the returned model has an different name.
        assertEquals("pluto",changedModel.getName());
        // verify that the old model still got the old name
        assertEquals("plutoXII",model.getName());

    }

    @Test
    public void update_model() throws JavaOrmenException {

        setUpWithMock();
        Model model = new DogModel("plutoXII",1234);

        DogModel  changedModel  = (DogModel) model.update();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();
        assertEquals("http://localhost:8081/api/dogs/plutoXII", mockApiImpl.getLatestExecutedRequestBuilder().getUrl());
        assertEquals(3, changedModel.getAge());
    }

    @Test
    public void find_all_instances() throws JavaOrmenException {

        setUpWithMock("twoPlutosInAList.json");

        List<Model> listOfDogs = DogModel.find.all();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals(1,listOfDogs.size());
        assertEquals("pluto",listOfDogs.get(0).getIdentifierValue());

    }
}
