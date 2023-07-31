# Instructions

## Database
1. connect to your mysql cli
2. create database name project_db;
3. use database project_db;

##Application Properties
1. go to src/main/resources/application.properties
2. change username & password if needed (those credentials should be equal to your mysql server credentials)

## Run API
1. Launch IntelliJ
2. Press Play
3. Launch Postman
4. Press File > Import 
5. Select bankApi.postman_collection.json
6. Send requests

## More info about spring framework
>1. Controller contains endpoints
>2. Endpoints are moving to service
>3. Service is moving to repository (or DAO, where you connect with DB)
>4. Model is the POJO classes
>5. DTO is for extra safety , in order Service and Controller don't touch the data of DB as it is 
>6. Converter converts DTO to POJO and POJO to DTO , it is mainly used on service
