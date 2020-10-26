#!/bin/bash

SCRIPT_PATH="$(cd "$(dirname "$0")" && pwd -P)"

cd "${SCRIPT_PATH}"

./compile-and-run.sh && say "Success!" || say "Fail! Fail!"
