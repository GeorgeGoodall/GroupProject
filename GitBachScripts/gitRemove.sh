#!/bin/bash
echo enter
i=0
for FILE in "$@"
do
	if [ $i -eq 0 ]
		then
			echo "changing dir to " + $FILE
			cd $FILE
		else
			echo $FILE
			git rm $FILE
		fi
		((i=i+1))
done