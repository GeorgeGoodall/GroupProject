#!/bin/bash
i=0
for FILE in "$@"
do
	if [ $i -eq 0 ]
		then
			cd $FILE
		else
			git rm $FILE
		fi
		((i=i+1))
done