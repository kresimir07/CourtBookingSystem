~~* Add logging messages and HTTP response requests for Role controller class~~
~~* "/api/roles/remove-from-user~~
~~Ne radi, treba istraziti sto moram napisati u body da bi to radilo~~
~~* DELETE ROLES ne radi zbog foreign keya~~
~~* /surface/surface-to-save- ne funkcionira kako treba isto treba biti POST~~
~~* /api/courts/courts-to-save mora biti kao POST~~

***
Important updates to perform for final version: 
- *Edit security config file to add only routes for admin privileges, while other routes will be considered as non admin routes*
- *Validation that booking cant be made in history*
- *Validation to check if the court is busy in requested time*
- *Implement working hours of specific court and appropriate booking time and messages*
- *Implement price for the booking reservation*
- *Modify security request/access for :*
  - *Booking confirmations*
  - *Admin privileges*
  - *Check all privileges*
  
- *Check privileges for both USER role, as it currently can edit user data, which means it can assign itself to ADMIN role*

***
Future updates to implement: 
- *Messages to Admin that there is a booking that needs to be confirmed, messages to user that his booking has been confirmed*
- *Implement entire history of booked courts by user - this will require to change my application properties ddl-auto to update*
- *Option for user to modify their own booking*
- *Option for ADMIN to modiy ALL created bookings*









    