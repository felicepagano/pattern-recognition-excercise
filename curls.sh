#!/bin/bash

 curl --request POST \
    --url http://localhost:9000/point \
    --header 'content-type: application/json' \
    --data '{
  	"x": 1.0,
  	"y": 1.0
  }'

  curl --request POST \
    --url http://localhost:9000/point \
    --header 'content-type: application/json' \
    --data '{
  	"x": 2.0,
  	"y": 2.0
  }'

curl --request GET \
    --url http://localhost:9000/space

curl --request GET \
  --url http://localhost:9000/lines/2

curl --request DELETE \
  --url http://localhost:9000/space