# Photo-shop
This is the web platform which consumer can buy or sell their pictures while they are traveling.

## Motivation
This project has been started to learn and combine Travel and E-Commerce domain to study. Understanding and Keeping tracking of the current domain architecture to advanced level. Developing and Updating the basic architecture whenever studying new approach based on the ATDD/TDD and keeping the flexible code as I update it new version is the main goal     

## Build Status
Planning on using Travis CI as a continues integration tool. The test and build status will be seen in this section

## Language/Framework
### Server
- Java8
- SpringBoot
- JPA/Hibernate
- QueryDsl
- MariaDB/H2
- Junit5/RestAssured
- AWS

### Frontend
- React.js
- Next.js
- Emotion

## Installation
### Server
```$xslt
./gradlew build
java -jar /build/libs/*.jar
```

### Frontend
```$xslt
cd ./web
npm install
npm start
```

## Test
```$xslt
./gradle test
```

## Author
Byeonggil Park