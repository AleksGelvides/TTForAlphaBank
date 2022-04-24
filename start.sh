#! /bin/bash
docker build -t for_alpha ./
docker run -d -p 8080:8080 for_alpha
