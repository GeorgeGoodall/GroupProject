#!/bin/bash
for FILE in "$@"
do
	git rm $FILE
done