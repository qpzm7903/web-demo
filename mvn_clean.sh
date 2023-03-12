#!/bin/bash
for dir in ./*/
do
    cd "$dir"
    if [ -f "pom.xml" ]
    then
        mvn clean
    fi
    cd .. 
done

