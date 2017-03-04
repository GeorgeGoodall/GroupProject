#!/bin/bash
echo enter
cd "$1"
echo "set dir to : " "$1"
git commit -m "$2"