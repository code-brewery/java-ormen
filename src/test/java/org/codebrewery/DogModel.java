package org.codebrewery;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by ejeserl on 9/19/15.
 */
@ResourceUrl(url = "dogs")
public class DogModel extends Model {

    private String name;

    private int age;


    public DogModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    DogModel() {
        this.name ="";
        this.age = 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getIdentifierValue() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Finder find = new Finder(DogModel.class);
}
