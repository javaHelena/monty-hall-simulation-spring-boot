# Monty Hall Simulation - Spring Boot Rest WS

Prerequisites: Java 1.8, Maven 3.5.

This simulation application will show that when choosing to switch to the remaining box - according to the Monty Hall Problem Strategy - the number of wins (selecting a box that contains a prize) will be arroiung 66 %. 


To package the application run the following command: 

    mvn clean install  
    

To run the application - use the jarfile created in the previous step. Make sure the port 8080 i available: 

    java -jar target/monty-hall-ws-1.0.0.jar

To access the application, go to your browser: 

    http://localhost:8080/play?iterations=10000&strategy=SWITCH
    http://localhost:8080/play?iterations=10000&strategy=KEEP
    http://localhost:8080/play?iterations=10000&strategy=RANDOM


To run the tests from the command line using maven: 

    mvn clean install  -Dtest=**/*IT
