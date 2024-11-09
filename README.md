![IronHackLogo](https://raw.githubusercontent.com/kresimir07/CourtBookingSystem/refs/heads/master/IronhackLogo.png)

# Final Project | Court Booking System

----


## Project Description

----

The Court Booking System is a fully-featured application designed to facilitate the booking and management of sports courts.  
It provides a seamless and efficient way for users to reserve courts for various sports activities—such as tennis—through an online interface.  
The system includes several core functionalities:
* User Management: Enables administrators to manage system users, including creating, updating, and deleting user profiles.
* Court Management: Administrators can manage courts and their attributes, including adding new courts, updating court details, and assigning surfaces to courts.
* Booking Management: Allows users to book courts for specific times, ensuring that courts are available and booked efficiently. It also includes booking confirmation capabilities.

## Class Diagram

----
## Setup
In order to initialize the Court Booking System application, follow the steps below:  

1.  Ensure port 3306 is free:  
    The application runs on http://localhost:3306, so ensure this port is not being used by another application.
2.  Database Setup:  
*    Create Database: You will need a MySQL database named tennis_club_db.  
*    Configuration: Configure the database connection parameters in the application.properties file.    
    Provide the database URL, username, and password. The application.properties file is typically locatedin the src/main/resources directory.  

```
   spring.datasource.url=jdbc:mysql://localhost:3306/tennis_club_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
```
3. Install dependencies:
```
mvn install
```
4. Run the application:
```
mvn spring-boot:run
```
5. Accessing the Endpoints:  
  
* Login:  
To access the endpoints, you need to log in with an existing user to obtain an authentication token.  
Login Endpoint: Perform a GET request to http://localhost:3306/api/login with appropriate credentials (username and password).  
* Authentication Token:  
Upon successful login, you will receive an authentication token. This token should be included in the header of subsequent requests to other endpoints.  
If you are using POSTMAN for requests you can use Postman's request collection from GitHub repository  

   https://github.com/kresimir07/CourtBookingSystem/blob/master/Tennis_IronHack_final.postman_collection.json




## Technologies Used

----

The technologies used for this project have been Java as a programming language, using IntelliJ as an IDE.  
Maven has been used as a package manager to inject the dependencies, downloaded from the cloud, necessary to carry out and compile the project,  
or to facilitate coding. For example, the Lombok library has been used to reduce the amount of boilerplate code and speed up repetitive code.  
The set of Spring Boot libraries that configure the Spring framework and thus allow establishing the structure of the project by layers (model, controller, repository, service)   
have also been used. This Spring context makes it easy to create the application by managing, for example, the connection and queries to the database through requests to the built  
controllers. In this case MySQL is used as the relational database management system to store and retrieve data.  
Finally, JPA (Java Persistence API) has been used to very simply map Java objects to entities in the relational database, without having to write SQL queries directly.  




## Controllers and Routes structure

----

* Description of POST, GET, PUT and DELETE type routes:

BookingController
GET /bookings: Retrieve all bookings.  
POST /bookings: Create a new booking request.  
PUT /bookings/{id}/confirm: Confirm a booking.  
GET /bookings/occupied: Check if a court is occupied. 

CourtController  
GET /courts: Retrieve all courts.  
POST /courts: Add a new court.  
PUT /courts/{id}: Update court details.  
PUT /courts/{id}/surface/{surfaceId}: Add or modify a surface to a court.  

UserController  
GET /users: Retrieve all users.  
POST /users: Add a new user.  

SurfaceController  
GET /surfaces: Retrieve all surfaces.  
POST /surfaces: Add a new surface.  

RoleController  
GET /roles: Retrieve all roles.  
POST /roles: Add a new role.  

* Security Configuration (in SecurityConfig.java)  

The security configuration ensures that specific endpoints are accessible only by authenticated and authorized users.  
Currently configuration of environment has been made in the following way.  
Only allowable route for ROLE_USER is :

```
/api/bookings/createNew   - ALOWS USER TO CREATE NEW BOOKING
```

In future implentation user will have also accces to route to create new user. 
But first i need to implent in that rout that only USER_ROLE can be created.  
Or to create an option to create only USER_ROLE with USER as default role.   

ALL the other routes can be performed only by Admin, which can be visible from SecurityConfig class 
```
.requestMatchers("/api/login/**").permitAll()
                .requestMatchers(POST, "/api/bookings/createNew").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().hasAuthority("ROLE_ADMIN"));
```



## Extra Links

----

Trello Board: 
Presentation:
Google Slides: 
Planio


## Future Work

----

* Notifications: Implement email/SMS notifications for booking confirmations and reminders.
* Payment Integration: Integrate payment gateways for booking payments.
* Enhanced Reporting: Add features for generating detailed reports on bookings and court usage.
* Frontend Developing: Build FrontEnd environment based on this backend development
* Hopefully hand it off once fully done to local tennis court for easier booking managment system

## Resources 

----

[Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)  
[Spring Data JPA](https://spring.io/projects/spring-data-jpa)  
[Lombok](https://projectlombok.org/)  
[W3 School](https://www.w3schools.com/java/)  
[Google](https://google.com/)  
[IronHack Student Portal](https://ironhack.com)  


## Team members

----

[Krešimir Vulić](https://github.com/kresimir07) 







