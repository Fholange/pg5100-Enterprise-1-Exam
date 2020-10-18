
# Enterprise 1 Exam
I have copied most of the dependencies and plugin management from the main repo.
When i have copied or adapted code from class i have stated so in that file. 

### How to run and access the server
You start the local server by running LocalApplicationRunner in the fronteds test folder(frontend/src/test/java/no/kristiania), 
and you can access it on localhost:8080. 

### Features

##### Backend
I have 3 entities, Movie, Review, User. The service Classes i have added functionality to:
create a user
• create/delete a movie
• add review to a movie, with a 1 to 5 star
• compute average of stars per movie
• retrieve all movies, sorted by average stars
• retrieve all reviews for a movie, sorted by either stars or creation time of the review

 I have Integration tests for each of the service classes with at least 70% coverage.
 
 ##### Frontend
• Homepage: display all movies (info summaries)
  
• Movie details page: shows all reviews and their stars. Possibility to sort reviews by time and
by stars. If the user is logged in, there is possibility to write a new review. A user can only write
one review per movie.
 




