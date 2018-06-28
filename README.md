#Pattern Recognition


##built with gradle 4.8.1. It requires java 10 to compile and run.

You can use the provided gradle 

###Gradle command list:

_**`gradle bootRun`**_ - will start a spring boot application on port 9000 (using Apache Tomcat 8.5 as Servlet Engine). 
You can configure the port modifying the application.yml file.

_**`gradle eclipse`**_ - Generates all Eclipse files.
 
_**`gradle idea`**_ - Generates IDEA project files (IML, IPR, IWS)
 
_**`gradle tasks --all`**_ will display all available tasks.

Actually there is only one implementation of the service that is the **_`InMemEagerSpaceService`_**

This implementation will use an in memory data structure to store the points. Every time that a point is added a
computation will start to find all the functions that belong to the given point. The functions are discovered with a
brute force algorithm.

* **_`POST /point`_** will return 201 OK if the point is added. If a point already exists it will return 409

* **_`DELETE /space`_** will return 200 OK.

* **_`GET /space`_** will return 200 OK.

* **_`GET /lines/{n}`_** will return 200 OK if at least one match is found, otherwise it will return 404.