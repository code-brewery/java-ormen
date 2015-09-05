[![Build Status](https://travis-ci.org/code-brewery/untappd.svg?branch=master)](https://travis-ci.org/code-brewery/untappd)

# java-ormen
Object relation mapping library for REST API:s in Java. It is designed to build applications that are powered by a RESTful API instead of a database. 

The Async java-ormen library purpose is to allow Java applications to easily execute HTTP requests and asynchronously process the HTTP responses, without the developer needing to think about the http requests. 

The library creates an abstraction layer on top of REST. The user of this library will in fact know very little about what protocol is used. 

The library has been designed using the Future API. If you you don't need a synchronous library you can tell the library to execute query's synchronous.  




## Usage

Lets say that we have a REST API that exposes CRUD Operations for fetching Dog´s. Well dogs can bark, so we will use dogs in this example.
 
As an developer you will probably create some POJOS for a java representation of the Dogs. Then you will probably write some kind of handler that takes these objects and sends them

back and forth the REST API. Well, you will not need to do that any longer. 


One just simply do the following:
 
Start of by creating a normal POJO, but we will make sure it extends AbstractRestModel. This will give the POJO super powers ( almost ).  Well it will give the pojo possibility interact with an rest api. 
 
The AbstractRest model gives you  4 public methods for interacting with the rest api, one for each CRUD operation. The good part here is that these methods takes an ActionCompletedinterface as parameter, so you can work with async request. More about that later. here is the code.


```java

public class DogModel extends AbstractRESTModel {

    // private vars that shall map against the json data fetched

    public final String name;

    public final int age;

    public DogModel() {

        name = "pluto";
        age = 5;
    }

    public DogModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected String resourceUrl() {

        return "/rest/dogs/";

    }

    @Override
    protected String identifierValue() {

        return this.name;
    }

    @Override
    public final AbstractRESTModel  parse(JSONObject responseJson) {
        String name = (String) responseJson.get("name");

        return new DogModel(name,123);

    }
    // dedicated method
    public String bark() {
        return "VOFF says" + this.name;
    }



}
```


The Dog model has a method called ```resourceUrl```. This url will point on to the url location where we can work on dogs. The Dog model has a method called  ```identifierValue```. It will be appended on the url on RUD operations. The ```parse``` method is there so you can convert raw json data to DogModel objects. This method will be called every time data is fetched from the server. ( the method will be a bit automagic in the future and populate the model with values without you needing to writing a parser ).


So now we have created a model. Its now time to use it to fetch data. Simply write the following in your code. Remember, these operations are async so we will add a sleep in the end ( only for educational purpose ).


```java
public class App
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException, IOException {
        // create a new client


        System.out.println("fetching the dog");
        DogModel pluto = new DogModel();
        pluto.fetch(new ActionCompletedInterface() {
            public void onDone(AbstractRESTModel model) {

                DogModel fetchedModel = (DogModel)model;

                System.out.println("it went good " + fetchedModel.bark());

            }

            public void onError(Throwable e) {

                System.out.println("it went bad" + e.getMessage());
            }
        });


        Thread.sleep(4000);
        System.out.println("dying");

    }
}
```

In the code above, the dog model will call the rest api when the fetch method is invoked. When the server responds the onDone or onError will be fired.



# LICENSE
