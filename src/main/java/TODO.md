* Check privileges for both USER role, as it currently can edit user data, which means it can assign itself to ADMIN role
* Add logging messages and HTTP response requests for Role controller class

* /surface/surface-to-save- ne funkcionira kako treba isto treba biti POST
* /api/courts/courts-to-save mora biti kao POST
* 
* Edit security config file to add only routes for admin privileges, while other routes will be considered as non admin routes
 
  //@PostMapping("/courts")
  //public ResponseEntity<Court> addCourt(@RequestBody Court court) {
  //    Court createdCourt = courtService.saveCourt(court);
  //    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourt);
  //}


  //        In summary, PUT is used for creating or replacing resources,
  //POST is used for creating or appending data to resources,
  //and PATCH is used for partially updating existing resources.