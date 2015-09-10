[![Build Status](https://travis-ci.org/code-brewery/untappd.svg?branch=master)](https://travis-ci.org/code-brewery/java-ormen)
[![codecov.io](http://codecov.io/github/code-brewery/java-ormen/coverage.svg?branch=master)](http://codecov.io/github/code-brewery/java-ormen?branch=master)
# java-ormen
Java-ormen is an Object relation mapping library for REST API:s in Java. It is designed to build applications that are powered by a RESTful API instead of a database. 

Thos async library purpose is to allow Java applications to easily execute HTTP requests over an REST API, and create an abstraction on top of REST, so that only the CRUD operations are exposed.  

The library has been designed using the Java CompletableFuture. If you you don't need a ascynchronous library you can tell the library to execute query's synchronous.  

The library uses the Jackson ObjectMapper, so its possible to use jackson annotations to describe how objects shall be marshalled and unmarshalled. 

The library strives for creating immutable objects. This means that you can have control over your objects. 

## Usage

Lets say that we have a REST API that exposes CRUD Operations for fetching Dog´s. Well dogs can bark, so we will use dogs in this example.

As an developer you will probably create some POJOś as an a java representation of the Dogs. Then you will probably write some kind of handler that takes these POJO instance´s and sends them

back and forth the REST API. Well, you will not need to do that any longer. 


One just simply do the following:
 
Start of by creating a normal POJO, but we will make sure it extends RestModel. This will give the POJO super powers ( almost ).  Well it will give the pojo possibility interact with an rest api. 
 
The Rest model gives you 4 public methods for interacting with the rest api, one for each CRUD operation. The good part here is that these methods returns a Completable future. 

Look at the comments, it will give you some hints about what information you need to provide for successfully send requests over rest. 

```java

public class MockRestModel extends RESTModel {

    /**
    * Default constructor, this is needed by the jackson json mapper. so always include it.
    **/
    public MockRestModel() {
        this.name = "";
    }

    public MockRestModel(String name) {
        this.name = name;
    }
    /**
    *
    * The return value of this method will indicate the resource url. The resource url points to the collection of resources that the pojo targets
    **/
    @Override
    String resourceUrl() {
        return "dogs";
    }
    /**
    * This method returns the identifying data for this resource.  for example this might be this.id or this.name or something else. 
    * 
    **/
    @Override
    String identifierValue() {

        return "identifier";

    }


}
```

The good part with this model is that we will not need to write any marshall or unmarshalling logic. This is handled by the RestModel class. But if you get into trouble you can use jackson json annotations on your fields. For example there might be times when you want to exclude some data from de json marshalling, or perhaps rename.  


So now we have created a model. Its now time to use it to fetch data. Simply write the following in your code. Remember, these operations are async so we will add a sleep in the end ( only for educational purpose ).


```java

        
        // 1. create a dog
        DogModel pluto = new DogModel();
        
        // 2. start to fetch the dog, take care of the completable future.
        CompletableFuture<RESTModel> futurePluto = pluto.fetch();
        
        
        future.thenApply(result -> isDone(result));      // sync callback

        Thread.sleep(4000);
        System.out.println("dying");



```

In the code above, the dog model will call the rest api when the fetch method is invoked. A CompletableFuture is returned, and we can work with that future as an promise.  



# LICENSE
The MIT License (MIT)