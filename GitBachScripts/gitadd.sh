#!/bin/bash
echo enter

a=0
for FILE in "$@"
do
	git add $FILE
done