# Patients Metadata Service

### Prerequisite
```
1. make sure Java is installed 
2. make sure maven is installed
3. make sure mongodb is running
4. Run application as springboot application
```
### How to test

##### To Create Patient
curl -X POST -H 'Content-Type: application/json' -d '{"patientName":"ABC123","gender":1, "contactNumber":"2342342345","email":"patient1@gmail.com"}' http://localhost:8081/patients/v1

##### to List Patients
curl -iv http://localhost:8081/patients/v1

### TODO
```
  1. See how you can handle dates
  2. implement pagination on get query
  3. Exception handling?(404, 500, 400)
  4. Filtering on query: ex: by age, by gender
  5. Create Address resource and join patient with billing and residential address and return whole object
  6. Advanced:
     Take patient and address objects as input with dto and convert them into patient and address mongo domain objects and save them together with transactions
  7. Once this is done, we can create domain objects and repository for aws dynamodb.
  8. After successfully saving we can emit event to kinesis stream.
  9. Write unit test cases and integration test cases
  10. Creating dockerfile and deploying it to local docker container
  11. deploying same docker container to aws
  12. Create lambda to consume events from kinesis and process them
  13. Explore cloudwatch logs and monitoring
 ```