#!/bin/bash

# Create a Jenkins group and user
groupadd -g 1000 jenkins
useradd -k /etc/skel/ -m -u 1000 -g 1000 -G docker -r -s /bin/bash jenkins

docker-compose up -d
