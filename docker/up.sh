#!/bin/sh
docker-compose -p narpa up -d --remove-orphans $*
