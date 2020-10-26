#!/bin/bash

SCRIPT_PATH="$(cd "$(dirname "$0")" && pwd -P)"

cd "${SCRIPT_PATH}/mapperWebApp/target/"

java -jar mapperWebApp-0.0.1-SNAPSHOT.jar
