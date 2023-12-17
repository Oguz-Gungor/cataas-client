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
* To apply changes, stop container and run docker compose up again

## Test & usage

* Application uses swagger ui, it can be reached via http://localhost:8082/swagger-ui/index.html

## Technologies & Libraries
* Java 21
* PostgreSQL
* Spring Framework