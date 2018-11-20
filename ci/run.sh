#!/bin/bash

REGISTRY_FQDN=registry.uggla.fr

# Create a Jenkins group and user
groupadd -g 1000 jenkins
useradd -k /etc/skel/ -m -u 1000 -g 1000 -G docker -r -s /bin/bash jenkins
# Open the docker socker widely (do not do this on production system
chmod o+rw /var/run/docker.sock

# Get certificate from registry
curl -L http://${REGISTRY_FQDN}:81/ca.crt > /usr/local/share/ca-certificates/ca-registry.crt
update-ca-certificates
systemctl restart docker.service

docker-compose up -d
