# RESTful API with Java Spring

Actions supported in this project:
1. Create new customer and new bank account connected to this person.
2. Deposit,withdraw or transfer money to/from your account.
3. Connect with SQL database and update db with every action.

# Instructions

## Database
+ connect to your mysql cli
+ create database name project_db;
+ use database project_db;

## Application Properties
+ go to src/main/resources/application.properties
+ change username & password if needed (those credentials should be equal to your mysql server credentials)

## Run API
+ Launch IntelliJ or another ide
+ Press Play
+ Launch Postman
+ Press File > Import 
+ Select bankApi.postman_collection.json
+ Send requests

## More info about spring framework
1. Controller contains endpoints
2. Endpoints are moving to service
3. Service is moving to repository (or DAO, where you connect with DB)
4. Model is the POJO classes
5. DTO is for extra safety , in order Service and Controller don't touch the data of DB as it is 
6. Converter converts DTO to POJO and POJO to DTO , it is mainly used on service
