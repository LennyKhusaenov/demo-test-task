# Test Task

## Spring MVC Application

Requests are sent to the address {protocol}://{host}:8096/ (port number defined in application properties)

1. Client send GET request to server
2. Server initialize session (session ttl = 30 min)
3. Checks if in the database user with this session ID
..* if exists, then fills in the model attributes with the user data and the sectors
..* else - fills model attributes with new user data and sectors
4. The user fills the form with data and sends a POST request to the server
5. The server checks if there is such a user in the database. If there is - updates the data, otherwise creates a new one.
6. Redirects the user to the root address "/"
7. The information selected by the user is saved on the page
8. User can edit data during the time of the session

The technologies used are listed in pom.xml

backup and insert scripts are located in resources/sql directory