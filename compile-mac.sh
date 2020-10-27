#!/bin/bash

SCRIPT_PATH="$(cd "$(dirname "$0")" && pwd -P)"

cd "${SCRIPT_PATH}"

./compile.sh && say "Success!" || say "Fail! Fail!"
