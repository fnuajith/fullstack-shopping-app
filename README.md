# Shopping application

A shopping application that
- shows the product list in the home page
- provides ability to edit and change product name and descriptions
- provides ability to delete the product

### Architecture ###
The application is built using Angular 11 on the front end, Java SpringBoot 2.4.5 in the backed and a MongoDB atlas database.
The application also publishes messages to a kafka topic whenever there is a product update.

### Angular application ###  
- Application is broken down into separate components, logically divided by their functionalities.
- Usage of Angular CLI to generate the project, components and services  
- Usage of ```ReactiveFormsModule```
- Usage of ```HttpClientModule``` to make http calls to the REST APIs
- Usage of ```rxjs``` observables to communicate changes to different components in the application using ```Subject``` and ```Subscription```
- Usage of Angular Routing
- Usage of interceptor to intercept Http errors
- Usage of Angular material for component library, theme and some responsiveness

### Java Springboot application ###  
- Exposes REST APIs to retrieve, add, edit and delete products (GET, POST, PUT, DELETE)
- ```CORS``` configuration
- Connects to a ```Mongo Atlas``` database
- Built using ```springboot```
- Has a Kafka Producer and Consumer implementation included using ```spring-kafka```
- ```Swagger``` documentation
- ```AOP``` for logging around the methods
- Unit tests with ```JUnit``` and ```Mockito```

### MongoDB ### 
- Usage of free sandbox offering from mongodb - [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

### Kafka and Zookeeper local setup ###
- Download and unzip Apache Kafka Binary (https://kafka.apache.org/downloads)
- Place the unzipped folder under 'C:\'
- Update path variables - add link to C:\kafka_2.13-2.8.0\bin\windows to be able to run commands from anywhere
- Create 'data' folder under 'C:\kafka_2.13-2.8.0'
- Under 'data', create 'zookeeper' and 'kafka' folders
- Update zookeeper.properties: dataDir=C:/kafka_2.13-2.8.0/data/zookeeper
- Update server.properties: log.dirs=C:/kafka_2.13-2.8.0/data/kafka
- Start Zookeeper in one command line: zookeeper-server-start.bat config\zookeeper.properties
- Start Kafka in another command line: kafka-server-start.bat config\server.properties

**** Leave the 2 command prompts running ****
- Open a new command prompt and run the below command to create a topic
```kafka-topics --bootstrap-server localhost:9092  --topic events_topic --create --partitions 3 --replication-factor 1```
