java -jar ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar &
sleep 30s
java -jar ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar &
sleep 45s
java -jar LoanService/target/LoanService-0.0.1-SNAPSHOT.jar &
java -jar MobileWalletService/target/MobileWalletService-0.0.1-SNAPSHOT.jar &
java -jar NotificationService/target/NotificationService-0.0.1-SNAPSHOT.jar &
java -jar PaymentService/target/PaymentService-0.0.1-SNAPSHOT.jar &
