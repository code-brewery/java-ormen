package org.codebrewery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.easymock.EasyMock.expect;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.*;



/**
 * Created by ejeserl on 9/20/15.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiFactory.class)
public class TestModelNegative {


    private ApiConfig apiConfig;
    private MockDefaultApiImplementation mockApiImpl;
    /**
     * All these tests will use powermock.
     *
     */
    public void setUpWithMock() {

        setUpWithMock("plainPluto.json",null);

    }

    public void setUpWithMock(String fileName) {
        setUpWithMock(fileName,null);
    }
    public void setUpWithMock(ExecutionException exception) {
        setUpWithMock(null, exception);
    }
    public void setUpWithMock(String fileName,ExecutionException e) {
        // 1. create config
        // 2. create mock
        // 3. init mock

        apiConfig = new ApiConfig.ConfigBuilder().apiLocation("api").port("8081").host("localhost").build();

        if(e != null) {
            mockApiImpl = new MockDefaultApiImplementation(apiConfig, e);
        } else {
            mockApiImpl = new MockDefaultApiImplementation(apiConfig, fileName);
        }// We create a new instance of test class under test as usually.

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(ApiFactory.class);

        expect(ApiFactory.getDefaultImplementation()).andReturn(mockApiImpl);

        // Note how we replay the class, not the instance!
        PowerMock.replay(ApiFactory.class);

    }



    @Test(expected = JavaOrmenException.class)
    public void save_model() throws JavaOrmenException {
        setUpWithMock(new ExecutionException("BOM",new IOException("failed to save the model")));
        Model model = new DogModel();

        DogModel  changedModel  = (DogModel) model.save();


    }



    @Test(expected = JavaOrmenException.class)
    public void delete_model() throws JavaOrmenException {
        setUpWithMock(new ExecutionException("BOM", new IOException("failed to save the model")));
        Model model = new DogModel("pluto",123);

        model.delete();

    }



    @Test(expected = JavaOrmenException.class)
    public void fetch_model() throws JavaOrmenException {
        setUpWithMock(new ExecutionException("BOM",new IOException("failed to save the model")));
        DogModel model = new DogModel("plutoXII",4);

        DogModel  changedModel  = (DogModel) model.fetch();

    }

    @Test(expected = JavaOrmenException.class)
    public void update_model() throws JavaOrmenException {

        setUpWithMock(new ExecutionException("BOM",new IOException("failed to save the model")));
        Model model = new DogModel("plutoXII",1234);

        DogModel  changedModel  = (DogModel) model.update();

    }

    @Test(expected = JavaOrmenException.class)
    public void find_all_instances() throws JavaOrmenException {

        setUpWithMock(new ExecutionException("BOM", new IOException("failed to save the model")));

        List<Model> listOfDogs = DogModel.find.all();



    }
}
