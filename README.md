# File Upload Server (Pure Java)

Simple HTTP server written in pure Java.
Supports file upload and stores metadata in PostgreSQL.



## Tech Stack
- Java 21
- Maven
- PostgreSQL
- JDBC
- HttpServer (JDK)


## How to Run

1. Configure PostgreSQL credentials
2. Run:
```bash
mvn clean compile
java -cp target/classes Main



---

###  Upload endpoint
```md
## Upload File

POST /upload

Headers:
- Content-Type: application/pdf
- X-Filename: example.pdf

Body:
- binary
