# Spring Security Labs

### What does this project do:

* Uses a form login
* Implements a customized `AuthenticationProvider` using a `UserDetailsService`
  to verify the user's credentials
* Implements a customized `UserDetailsService` using a Spring Data JPA 
  to get the users from the database

### How to use it
Create a database and put the credentials in the environment variables below:
```bash
export DATASOURCE_USER=...
export DATASOURCE_PASS=...

./gradlew bootRun
```

Additionally, you also have the possibility to create an `.env` file with the values for
the placeholders:
```bash
cat <<EOF > .env
DATASOURCE_USER=foo
DATASOURCE_PASS=bar
EOF
```

JPA will automatically create all tables and the data.sql will insert some
test data to run the application.
Go to http://localhost:8082 and enter the credentials (john/admin). It should
present the page with some products in a table format.
