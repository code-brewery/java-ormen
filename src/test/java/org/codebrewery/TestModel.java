package org.codebrewery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.easymock.EasyMock.expect;

import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.*;



/**
 * Created by ejeserl on 9/20/15.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiFactory.class)
public class TestModel {



    @Test
    public void save_model() throws JavaOrmenException {
        long expectedId = 42;
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation conf = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(conf);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

        Model model = new DogModel();

        DogModel  changedModel  = (DogModel) model.save();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals("pluto", changedModel.getName());
    }



    @Test
    public void delete_model() throws JavaOrmenException {
        long expectedId = 42;
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation mock = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(mock);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

        Model model = new DogModel("pluto",123);

        model.delete();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals("http://localhost:8081/api/dogs/pluto",mock.getLatestExecutedRequestBuilder().getUrl());
    }



    @Test
    public void fetch_model() throws JavaOrmenException {
        long expectedId = 42;
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation conf = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(conf);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

        Model model = new DogModel();

        DogModel  changedModel  = (DogModel) model.fetch();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals("pluto", changedModel.getName());
    }

    @Test
    public void update_model() throws JavaOrmenException {
        long expectedId = 42;
        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation conf = new MockDefaultApiImplementation(apiConfig,"plainPluto.json");
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(conf);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

        Model model = new DogModel("pluto",1234);

        DogModel  changedModel  = (DogModel) model.fetch();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct
        assertEquals(3, changedModel.getAge());
    }
    @Test
    public void find_all_instances() throws JavaOrmenException {

        ApiConfig apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();
        MockDefaultApiImplementation conf = new MockDefaultApiImplementation(apiConfig,"twoPlutosInAList.json");
        // We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(conf);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

        List<Model> listOfDogs = DogModel.find.all();

        // Note how we verify the class, not the instance!
        PowerMock.verifyAll();

        // Assert that the ID is correct

        assertEquals(1,listOfDogs.size());

    }
}
