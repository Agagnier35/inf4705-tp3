#!/bin/bash

OPTIONS=""
while [[ $# -gt 0 ]]
do
key="$1"

case $key in
    -e)
    EX_PATH=$2
    shift
    ;;
    -p)
    OPTIONS="${OPTIONS}${1} "
    ;;
    *)
        echo "Argument inconnu: ${1}"
        exit
    ;;
esac
shift
done
java -jar tp3-1.0-SNAPSHOT.jar "$EX_PATH" $OPTIONS
