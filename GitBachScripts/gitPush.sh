#!/bin/bash
echo enter
query="git push"
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
eval $query
y="eval $query"
echo $y

