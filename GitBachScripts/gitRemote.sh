#!/bin/bash
query="git remote"
i=0
for FILE in "$@"
do
	if [ $i -eq 0 ]
		then
			cd "$1"
		else
			query="$query $FILE"
		fi
		((i=i+1))
done
$query

