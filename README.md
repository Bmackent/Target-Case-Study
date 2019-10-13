# Target Case Study - myRetail Restful Service
I chose the first case study option as it looked like a good way to demonstrate what I learned at amazon. In addition, 
amazon had their ownbuild system. I used maven to build this project as a way to showcase my ability to quickly pick up 
new technologies and put them to use.

# Technologies Used
JDK 1.8.0

DynamoDB

Apache Maven 3.6.2

SpringBoot 2.1.6.RELEASE

# DynamoDB Setup 
I used the local version of DynamoDB for this project. See 
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html for details about usage. The basic 
setup steps are as follows:
1. Download the local version of DynamoDB
2. Extract the downloaded files to a directory of your choice
3. Open the chosen directory in command prompt
4. Run java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
5. You may need to run aws configure to set up access key region settings.
6. I added initialization code in ProductPricingDatabase.java that will populate the database with some pricing data for
 a few products. You will not need to add data yourself.

# Unit Tests
1. Open the myRetail directory in command prompt
2. Run mvn test

# Running the Application 
1. Ensure that your local version of DynamoDB is running
2. Open the myRetail directory in command prompt
3. Run mvn spring-boot:run

# Using the Requested APIs
As mentioned earlier, pricing data has been populated in the DynamoDB table. There is pricing data for the following 
products:
1. 13860428
2. 13860429
3. 13860431

### Responds to an HTTP GET request at /products/{id} and delivers product data as JSON
1. In a browser of your choice, go to http://localhost:8080/products/{id}
2. See that JSON data is returned for the requested product
![](https://raw.githubusercontent.com/Bmackent/Target-Case-Study/master/GetRequest.jpg)

### BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store
1. In a browser of your choice, go to http://localhost:8080/products/{id}
2. See that JSON data is returned and note the current pricing information
![](https://raw.githubusercontent.com/Bmackent/Target-Case-Study/master/GetRequest.jpg)
3. Using a method of your choice (I used an application called Postman), send a PUT request to 
http://localhost:8080/products/{id}. I copied my Postman request below:

        PUT /products/13860428 HTTP/1.1
        Host: localhost:8080
        Content-Type: application/json
        cache-control: no-cache
        Postman-Token: 716b6baa-8580-4dc0-8171-a67ccf0727ba
        {
            "id": 13860428,
            "name": "The Big Lebowski (Blu-ray)",
            "productPricingInfo":{
                "id":13860428,
                "price":15.99,
                "currencyCode":"USD"
            }
        }
4. Refresh the page in your browser and see that the data has been updated as per the PUT request
![](https://raw.githubusercontent.com/Bmackent/Target-Case-Study/master/PutRequest.jpg)

# Further Improvements
There are a number of things I would want to do in this project before calling it production ready.
1. Additional unit tests
    * I created just a few unit tests for the model objects
    * I would've liked to create additional unit tests for each of the larger pieces of this project such as:
        * Controller
        * DynamoDB accessor
        * Service
    * These tests would likely be created by mocking out dependencies for each of the individual pieces
2. Integration tests
    * Creating integration tests to test how the pieces work together would be another must before calling this project
    production ready
3. Update the database configuration to use a real instance of DynamoDB
    * I used a local version of DynamoDB for development
    * A production ready version of this project would need to use a real instance of DynamoDB
    * The updates needed to make this change should be relatively small with most of the work being done on the AWS side
    of things.
4. Another somewhat less critical piece I'd like to add would be caching on the product information
    * If this were a real application, we would want to cache the results of our calls to the service we are making a 
    GET call to as well as the calls to DynamoDB.
5. Error Handling
    * If a user attempts to get information for a product that does not exist, they are greeted with a whitelabel error 
    page
    * Presenting the user with a 404 page and a useful error message would promote a better user experience
    * There are other such errors that this logic would also apply to, but the nonexistant product case would likely be 
    the most common. All such cases should be handled.

