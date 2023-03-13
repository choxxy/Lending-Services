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
