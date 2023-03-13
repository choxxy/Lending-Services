# LoanService



## How to run
### Requirements
1. Maven
2. Java JDK 17
3. IntelliJ Idea Ultimate  or Spring Tool Suite 64 bit 4.8.1  (optional)

### Steps

* Clone the repository from Github to a local directory.

* Ensure that the required software dependencies are installed on the system, including Java, maven and IDE.

* Change directory to project root and run `mvn clean package`. This command will run test and build the artifacts

* Unfortunately I have not found a  way of running all the services automatically,
  so the services will need to be started manually in the order below.
  1. java -jar ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar 
  2. java -jar ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar 
  3. java -jar LoanService/target/LoanService-0.0.1-SNAPSHOT.jar 
  4. java -jar MobileWalletService/target/MobileWalletService-0.0.1-SNAPSHOT.jar 
  5. java -jar NotificationService/target/NotificationService-0.0.1-SNAPSHOT.jar 
  6. java -jar PaymentService/target/PaymentService-0.0.1-SNAPSHOT.jar
  
  Alternatively run `run.sh`, you will need to kill each service with the kill command  

## Testing Endpoints with curl

1. Loan request endpoint
   - To get a list of products send the run the curl command below:-<br>  
   `curl -X 'POST'`\
   `'http://localhost:9095/api/loan/'` \
   `-H 'accept: */*'` \
   `-H 'Content-Type: application/json'` \
   `-d '{`
   `"customerId": 1,`       
   `"walletAccountId": "1"`      
   `}'`
     <br><br>
   - Response 
   `[{"maxAllowableLimit":1000,"interest":10.0,"tenureInDays":15,"id":1}]`

2. Process Loan
   <br>
   - Request <br>
   `curl -X 'POST'` \
   `'http://localhost:9095/api/loan/process-loan-request'` \
   `-H 'accept: */*'` \
   `-H 'Content-Type: application/json'` \
   `-d '{`\
   `"customerId": 1,`\
   `"walletAccountId": "1",`\
   `"loanProductId": 1`\
   `}'` <br><br>
   - Response
    `{"loanId":1,"loanProductId":1,"walletAccountId":"1","userId":1,"interest":100.0,"loanAmount":1000.0,"totalAmount":1100.0,"createdOn":"2023-03-13T16:45:05.601+00:00","dueDate":"2023-03-28T16:45:05.601+00:00","status":"ACTIVE"}%  `
<br><br>
3.  Make Payment 
<br>
    - Request<br>
    `$ curl -X 'GET'` \
    `'http://localhost:9095/api/payment/1/make-payment'` \
    `-H 'accept: */*'` \
    `-H 'Content-Type: application/json'`
   <br><br>
    To verify payment check Notification Service logs, or use http://localhost:9091/h2-console/ to check the database

