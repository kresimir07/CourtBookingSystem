![IronHackLogo](https://raw.githubusercontent.com/kresimir07/CourtBookingSystem/refs/heads/master/IronhackLogo.png)

# Final Project | 🎾 Court Booking System 🎾




## Project Description



"Welcome to the Tennis Sport House🎾 where you have an amazing opportunity to book some of the finest courts we offer! Whether you feel like Rafael Nadal  on the 'Philippe Chatrier Court,' Novak Djokovic at 'Palexpo,' Roger Federer 🐐 on 'Centre Court,' or Andy Murray at 'Rod Laver Arena,' our booking system is here to provide a seamless experience. Choose your court and start playing like a champion! 🥇"  


The system includes several core functionalities:
* User Management: Enables administrators to manage system users, including creating, updating, and deleting user profiles.
* Court Management: Administrators can manage courts and their attributes, including adding new courts, updating court details, and assigning surfaces to courts.
* Booking Management: Allows users to book courts for specific times, ensuring that courts are available and booked efficiently. It also includes booking confirmation capabilities.

## Class Diagram



![tennis_club_db.jpg](https://raw.githubusercontent.com/kresimir07/CourtBookingSystem/refs/heads/master/tennis_club_db.jpg)
## Setup
In order to initialize the Court Booking System application, follow the steps below:  

1.  Ensure port 3306 is free:  

Application runs on http://localhost:3306, so ensure this port is not being used by another application.   


2.  Database Setup:  
*    Create Database:  
You will need a MySQL database named tennis_club_db.  
*    Configuration:  
Configure the database connection parameters in the application.properties file.    
Provide the database URL, username, and password. The application.properties file is typically located in the src/main/resources directory.  

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
* Login Endpoint: Perform a GET request to http://localhost:3306/api/login with appropriate credentials (username and password).  
Login can be performed either as ROLE_ADMIN, or ROLE_USER. Check assigned roles from sample data in class DataLoader, or directly from DB.
```
{
    "username" : "kvulic",
    "password" : "1234"
}
```

* Authentication Token:  
Upon successful login, you will receive an authentication token. This token should be included in the header of subsequent requests to other endpoints.  
If you are using POSTMAN for requests you can use Postman's request collection from GitHub repository  - Remember to update token header.

   https://github.com/kresimir07/CourtBookingSystem/blob/master/Tennis_IronHack_final.postman_collection.json


## Technologies Used



The technologies used for this project have been Java as a programming language, using IntelliJ as an IDE.  
Maven has been used as a package manager to inject the dependencies, downloaded from the cloud, necessary to carry out and compile the project,  
or to facilitate coding. For example, the Lombok library has been used to reduce the amount of boilerplate code and speed up repetitive code.  
The set of Spring Boot libraries that configure the Spring framework and thus allow establishing the structure of the project by layers (model, controller, repository, service)   
have also been used. This Spring context makes it easy to create the application by managing, for example, the connection and queries to the database through requests to the built  
controllers. In this case MySQL is used as the relational database management system to store and retrieve data.  
Finally, JPA (Java Persistence API) has been used to very simply map Java objects to entities in the relational database, without having to write SQL queries directly.  




## Controllers and Routes structure



### Description of POST, GET, PUT, PATCH and DELETE type routes:

I will provide 3 examples of routes in the code. 

### 1) Login  (Depends on entered credentials, option as admin, or user)
To log in to application there is route created  
/api/login - it requires user to enter some parameters in body of the request as JSON raw format ie:  

```
{
    "username" : "kvulic",
    "password" : "1234"
}
```
Allows you to login  by providing the username and password in the body of the request in JSON format.  
* Returns an HTTP status code 200 (OK) if added successfully.  
* Returns an HTTP status code 403 (Forbidden) if user is not found in database, or wrong credentials used. 

### 2) Create new user :

/api/users/createNew

To create new user we need to enter some parameters in body of the request as JSON raw format. Ie :  
```
{
        "name": "Roger Federer",
        "username": "rfederer",
        "password": "tennisGOAT"
}
```  
* Returns an HTTP status code 201 (Created) if added successfully.
* Returns an HTTP status code 403 (Forbidden) if registration is being performed by any other role than ADMIN.

### 3) Create new booking request :
For new booking we need to use following route :  

/api/bookings/createNew  
Followed by same approach to enter parameters in body of the request as JSON raw format. Ie :

```
{
    "userId": 2,
    "courtId": 4,
    "startTime": "2024-12-13T15:00:00",  // - Booking is made in time slots start - end, which returns also calculation price of the booking
    "endTime": "2024-12-13T15:31:00"
    
}
```  
_**In this example we also see implementation of robust mechanism HTTP RESPONSES built in application.**_  

These messages are not only visible in console output, they are also returning back as HTTP RESPONSE to POSTMAN for example.  

* Returns an HTTP status code 201 (Created) if added successfully.
* Returns an HTTP status code 404 (Not found) + HTTP RESPONSE message "User ID or court ID not found" if there is data mismatch. 
* Returns an HTTP status code 403 (Forbidden) + HTTP RESPONSE message "Booking cannot be made in past"  
if user tries to create booking in the past.  
* Returns an HTTP status code 409 (Conflict) + HTTP RESPONSE message "Court is already occupied at the requested time"  
If the requested time of booking is already occupied by another booking. 
* Returns an HTTP status code 403 (Forbidden) + HTTP RESPONSE message "End time is before start time"  
If user tries to make booking with endTime before startTime it will throw error: "End time is before start time" 

This example also provides us insight in multiple verify methods implemented in booking mechanism : 
```
verifyUserExistence;  - method to check if user exists in database 
verifyBookingTime;  - verify that booking time is not made in past
verifyCourtExistence;  - verification that court exists,
isCourtOccupied;  - verification that court is not occupied in that slot 
calculatePrice; - method which is written in Booking class to calculate total price of booking. Used 30 minutes time frame for calculation
```
---

Some other routes used in project:  

CourtController
```
POST "/api/courts/createNew" - Creates new court 
GET "/api/courts/all"  - Retrieve information about all courts from database
PUT "/api/courts/surfaceToCourt"  - Updating court with surface information
PATCH "/api/courts/{id}"  - Option to update court parameters
```
RoleController  
```
POST "/api/roles/createNew"  
GET "/api/roles/all" - Retrieve information about all roles from database
PUT "/api/roles/addToUser"  - Assigning, or modifying roles to user
PUT "/api/roles/removeRoleFromUser"  - Removing assigned role from user
DELETE "/api/roles/{id}"  - Deletes entry from database by given id
```
SurfaceController 
 ```
POST "/api/surface/createNew" - Creating new type of surface
GET "/api/surface/all"  - Retrieve information about all surfaces from database
DELETE "/api/surface/{id}"  - Deletes entry from database by given id
```
BookingController  
```
GET "/api/bookings/all" - Retrieve information about all bookings from database
PUT "/api/bookings/confirm" - ADMIN confirmation about booking, used Boolean. By default booking is FALSE until ADMIN changes are made
```
UserController
```
POST "/api/users/createNew"  - Creates new user
GET "/api/users/all"   - Retrieve information about all users from database
DELETE "/api/users/{id}"  - Deletes entry from database by given id
PATCH "/api/users/{id}" - Updates user information
```


* Security Configuration (in SecurityConfig.java)  

The security configuration ensures that specific endpoints are accessible only by authenticated and authorized users.  
Currently configuration of environment has been made in the following way.  
Only allowable route for ROLE_USER is :

```
/api/bookings/createNew   - ALOWS REGISTERED USER TO CREATE NEW BOOKING
```


In future implementation user will also have access to route to create new user.  
But first I need to implement 2 things, first a query to deny registration if user already exist,  
and option that creating new user will hide roles, and set user role as default.

ALL the other routes can be performed only by Admin, which can be visible from edited SecurityConfig class .
```
.requestMatchers("/api/login/**").permitAll()
                .requestMatchers(POST, "/api/bookings/createNew").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().hasAuthority("ROLE_ADMIN"));
```



## Extra Links






## Future Work


* Finalising application to have all backend functionalities 
* Notifications: Implement email/SMS notifications for booking confirmations and reminders.
* Payment Integration: Integrate payment gateways for booking payments.
* Enhanced Reporting: Add features for generating detailed reports on bookings and court usage.
* Frontend Developing: Build FrontEnd environment based on this backend development
* Hopefully hand it off once fully done to local tennis court for easier booking management system

## Resources 



[Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)  
[Spring Data JPA](https://spring.io/projects/spring-data-jpa)  
[Lombok](https://projectlombok.org/)  
[W3 School](https://www.w3schools.com/java/)  
[Google](https://google.com/)  
[IronHack Student Portal](https://ironhack.com)  


## Team members



[Krešimir Vulić](https://github.com/kresimir07) 







