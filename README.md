# rewards-calculator-api
This is a Spring Boot based RESTful microservice to calculate the rewards for the retail customers on their purchase.
H2 in-memory database has been used for DB. 
For credentials please refer the application.properties.
## Steps to run the project:
1. Start the application
2. Login to the H2 console in the browser using (http://localhost:8080/h2-console/)
3. Run the sql query available in the data.sql file for test data.
4. start the execution using using below swagger page /direct url.
   
    ### Swagger-ui page: 
        http://localhost:8080/swagger-ui.html
   
    ### direct url : 
        http://localhost:8080/rewards/{custmerId}   eg: http://localhost:8080/rewards/1
                    