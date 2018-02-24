#!/bin/bash

# Get qualitygate status

curl http://localhost:9000/api/qualitygates/project_status?projectKey=hello%3AHelloWorld | jq .
