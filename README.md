# Telegram-bot-demo
![imagename](https://res.cloudinary.com/edmonddantes/image/upload/v1613371442/Screenshot_from_2021-02-15_09-43-04_u1qiwg.png)
## About
A **Tourist Telegram bot** that provides information about cities, must-see places and local attractions. As well as API for performing CRUD operations on the list of cities

## What's inside
This project is based on the [Spring Boot](https://spring.io/projects/spring-boot "Spring Boot") project and uses these packages :
* [Maven](https://maven.apache.org/ "Maven")
* [Spring Web](https://spring.io/ "Spring Web")
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa "Spring Data JPA") ([Hibernate](http://hibernate.org/ "Hibernate"))
* [Telegram Bot API](https://core.telegram.org/bots/api "Spring Web")
* [H2 Database](https://core.telegram.org/api "Telegram Bot API")

## Configuration
To get username and token you need to create your bot [BotFather](https://t.me/BotFather "BotFather"). After add your credentials to ```/resources/bot.properties```. The default ones are :

```Java
username = XXXXXXXXXXXXXXXX
token = XXXXXXXXXXXXXXXX
```

## Installation
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

```
mvn clean install
```

## Usage
Run the project and now you can use your bot in Telegram.
## Endpoints

During all operations in the city object there must be at least one description.

To get a city with a description you need to execute this GET request, where change _cityName_ to the city you want to find.
```Java
GET http://localhost:8080/your-token*/api-telegram-bot?city=cityName
```

To create a city with a description, you need to execute this POST request, and in the request body there must be a City object in JSON format.
```Java
POST http://localhost:8080/your-token*/api-telegram-bot
```

To update a city with a description, you need to execute this PUT request, and in the request body there must be a City object in JSON format. ATTENTION! If the city with this name was not in the list, then it will be created.
```Java
PUT http://localhost:8080/your-token*/api-telegram-bot
```

To delete a city with a description, you need to execute this DELETE request, where change _cityName_ to the city you want to delete.
```Java
DELETE http://localhost:8080/your-token*/api-telegram-bot/cityName
```
*-put your personal token

## Author
Send me message to [Gmail](mailto:taras.zadziarnouski@gmail.com "Gmail") or [Telegram](https://t.me/taraszadziarnouski "Telegram") 

Add me to [LinkedIn](https://www.linkedin.com/in/taras-zadziarnouski-b6205a206/ "LinkedIn")

Follow me to [Instagram](https://www.instagram.com/zadziarnouskitaras/ "Instagram")
