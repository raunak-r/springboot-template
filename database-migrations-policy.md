# Steps
1. Create a new file in the api/src/main/resources/db/migration folder. 
2. Files must be named as per the following convention
    - V{number}__{short message indicating the change}.sql
3. The newly created file should only contain the sql statements required to update the Database.

## In case of a new Table Creation.
1. Do not create any sql file before following all the steps. 
2. Uncomment these lines in the application.properties file
spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql

3. Run the project simply, it will generate a create.sql file at the root ie. at /src/
4. In the create.sql, search for the particular table which is required to be created, copy the statement, any foreign key dependency etc, and use a sql formatter to pretty-print it.
5. Follow the top-most 3 steps.