* Check privileges for both USER role, as it currently can edit user data, which means it can assign itself to ADMIN role
~~* Add logging messages and HTTP response requests for Role controller class~~
~~* "/api/roles/remove-from-user~~
~~Ne radi, treba istraziti sto moram napisati u body da bi to radilo~~

~~* DELETE ROLES ne radi zbog foreign keya~~
~~* /surface/surface-to-save- ne funkcionira kako treba isto treba biti POST~~
~~* /api/courts/courts-to-save mora biti kao POST~~
* 
* Edit security config file to add only routes for admin privileges, while other routes will be considered as non admin routes
