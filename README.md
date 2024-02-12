# ecommerce

# Building and Running 

This document provides instructions on how to build and run the application.

## Prerequisites

Before proceeding, ensure that you have the following installed on your system:

- Java Development Kit (JDK) 17
- Apache Maven (https://maven.apache.org/download.cgi)
- Git (for cloning the project from a repository)

## Building the Application

Follow these steps to build the Spring Boot application using Maven:

1. Clone the project from the repository:
   git clone <repository_url>
2. Navigate to the project directory.
3. Execute the Maven build command:
   mvn clean install
4. This command compiles the source code, runs the tests, and packages the application into a JAR file.

## Running the application

Once the application is built, you can run it using the following steps:

1. Navigate to the target directory.
2. Run the JAR file using the `java` command:
   java -jar ecommerce-0.0.1-SNAPSHOT.jar
3. Once the application starts, you should see log messages indicating that the application is running. 
   By default, the application will start on port 8080. You can use the url http://localhost:8080 to access the REST endpoints.
   If not, you can interact with the program usoing the CLI.
   
## Interacting with the application
You can interact with the application through the command line or through the REST API endpoints.

You can find the detailed instructions to use the rest api endpoints below. (Prerequisites: Postman App or Postman Web)
View Postman collection here- 
[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/21991805-99ac7d92-45f1-448a-ae7c-e4028bf1d7d3?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D21991805-99ac7d92-45f1-448a-ae7c-e4028bf1d7d3%26entityType%3Dcollection%26workspaceId%3D368274ee-ca7e-4e5e-9022-420902d83708)

