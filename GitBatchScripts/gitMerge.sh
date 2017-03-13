#!/bin/bash
query="git merge"
i=0
for ARG in "$@"
do
	if [ $i -eq 0 ]
		then
			cd "$1"
		else
			query="$query $ARG" 
		fi
		((i=i+1))
done
$query