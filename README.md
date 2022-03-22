# UserLoginProject
Program to verify user account login credentials

# Technologies
This project was created using:
* Java version 17
* Apache Maven
* SQLite
* JSON Simple 1.1.1

# How to use
* The program will prompt the user to create/recreate a database to store user login information
* The user can add a username and password to the database
* Once the user's credentials have been added, the program will prompt the user to enter a username and password
* If the username and password are correct the message "Deep Dark Secret" is displayed, otherwise the message "Get Lost!" is displayed 

# Setup
To run this project, go inside directory where project is located and type in the command line: 
```
$ mvn compile
$ mvn exec:java -Dexec.mainClass="userLogin.userLogin"
```

# Additional Notes
* The user needs to create a JSON file that lists where the database is stored locally
* In UpdateSQLiteDatabase.java Line 24 should be changed to the location of the aforementioned JSON file
* In the project pom.xml be sure that both maven.compiler.source and maven.compiler.target are set to the latest Java version installed on your PC

# Installation
* Apache Maven (https://maven.apache.org/install.html)
* SQLite (https://www.sqlitetutorial.net/download-install-sqlite/)
* sqlite-jdbc-3.36.0.3.jar (https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/) [Select the "sqlite-jdbc-3.36.0.3.jar"]
* json-simple (https://code.google.com/archive/p/json-simple/downloads) [Select the "json-simple-1.1.1.jar"]
 
# Acknowledgements
This program is based on Project #6 of Robert Heaton's Programming Projects for Advanced Beginners 
[Twitter: @RobJHeaton]
(https://robertheaton.com/2019/08/12/programming-projects-for-advanced-beginners-user-logins/)

# License
This is a free open-source software.
