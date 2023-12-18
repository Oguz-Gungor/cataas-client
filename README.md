# CATAAS Client with Spring Framework

## Installation

* Clone Repository to local machine
* Install docker engine
* run docker compose command

```
    docker compose up
```

## Configuration
* Configurations can be made via compose.yml file. To change directories that files will be saved in, change following variables:
    * filteredDirectory
    * taggedDirectory
    * textDirectory
    * widthHeightDirectory
    * taggedTextDirectory
* To change application port, change first port address in server.ports to desired port address 
* To change scheduled file remove period, change prunePeriodInSeconds variable in compose.yml file
* To apply changes, stop container and run docker compose up again

## Test & usage

* Application uses swagger ui, it can be reached via http://localhost:8082/swagger-ui/index.html. Example api calls:
  * Get random cat photo with 600 width
    * http://localhost:8082/cat/filters?width=600&fileName=fileName
  * Get random cute cat
    * http://localhost:8082/cat/cute?fileName=fileName
  * Get random cute cat saying hello 
    * http://localhost:8082/cat/cute/says/hello?fileName=fileName
  * Get random cat saying hello
    * http://localhost:8082/cat/says/hello?fileName=fileName
  * Get file report
    * http://localhost:8082/file/report
* JobRunr has been used for scheduled jobs, dashboard for cron jobs can be reached via http://localhost:8082/swagger-ui/index.html

## Technologies & Libraries
* Java 21
* PostgreSQL
* Spring Framework