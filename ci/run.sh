#!/bin/bash

# Create a Jenkins group and user
groupadd -g 1000 jenkins
useradd -k /etc/skel/ -m -u 1000 -g 1000 -G docker -r -s /bin/bash jenkins
# Open the docker socker widely (do not do this on production system
chmod o+rw /var/run/docker.sock

docker-compose up -d
