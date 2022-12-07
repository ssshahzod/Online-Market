#!/bin/sh
sh -c 'docker start database'
#startTime=$(date -d="%D")
#printf %s "Started building at: " $startTime
#printf '\n'
sh -c 'mvn clean install && docker stop database'
