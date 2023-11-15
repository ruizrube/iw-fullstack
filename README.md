# iw-fullstack

## Sample project for *Ingeniería Web* course

This repo includes a sample project based on Spring and Vaadin java frameworks. It includes a sample view to show data from a database and a two-step registration user process. However, the app does not include any security mechanism.
The code is acommpanied by a suite of automated (unit, integration, e2e and acceptance) tests.

## Books module
This module provides a feature to show a data grid containing book data. 

Route: http://localhost:8080/books 

**Class diagram**

![UML class diagram](https://github.com/ruizrube/iw-fullstack/blob/d8c069d001c9b114cf2ddc1702daea935f563d25/models/Book%20Module%20Class%20Diagram.png?raw=true)

**Sequence diagram**

![UML sequence diagram](https://github.com/ruizrube/iw-fullstack/blob/d8c069d001c9b114cf2ddc1702daea935f563d25/models/BookView_showFilteredData.png?raw=true)



## User module 
This module provides two views to register and activate users. 

**Class diagram**

![UML class diagram](https://github.com/ruizrube/iw-fullstack/blob/d8c069d001c9b114cf2ddc1702daea935f563d25/models/User%20Module%20Class%20Diagram.png?raw=true)

### Register user
Shows a form to register a user on the system

Route: http://localhost:8080/ or http://localhost:8080/userregistration 

**Sequence diagram**

![UML sequence diagram](https://github.com/ruizrube/iw-fullstack/blob/d8c069d001c9b114cf2ddc1702daea935f563d25/models/UserRegistrationView_onRegisterButtonClick.png?raw=true)


### Activate user 
Shows a form to activate the user by introducing his/her email and secrect code received in the user mailbox

**Sequence diagram**

http://localhost:8080/useractivation 

![UML sequence diagram](https://github.com/ruizrube/iw-fullstack/blob/d8c069d001c9b114cf2ddc1702daea935f563d25/models/UserActivationView_onActivateButtonClick.png?raw=true)




## Setting up the application

Open application.properties and modify the following database server (e.g. MySQL) properties:
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password

Open application.properties and modify the following mail server (e.g. Gmail) properties:

- spring.mail.host
- spring.mail.port
- spring.mail.username
- spring.mail.password

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more
on [how to import Vaadin projects to different IDEs](https://vaadin.com/docs/latest/guide/step-by-step/importing) (
Eclipse, IntelliJ IDEA, NetBeans, and VS Code).




## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/fullstackwebapp-1.0.0.jar`
