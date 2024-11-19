#! /bin/bash

curl -X 'POST' \
  'http://localhost:8081/books' \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  -d '{
  "title": "Les dents de la mer",
  "author": "Peter Benchley",
  "description": "Un livre qui fait peur",
  "isbn": "XXXX",
  "releaseDate": "1974-11-19",
  "price": 20,
  "rating": 5
}'

curl -X 'POST' \
  'http://localhost:8080/movies' \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  -d '{
  "title": "Les dents de la mer",
  "director": "Steven Spielberg",
  "genre": "Action",
  "productionDate": "1977-11-19",
  "releaseDate": "1978-11-19",
  "description": "Un film qui fait peur",
  "rating": 5,
  "fromBook": 1
}'

