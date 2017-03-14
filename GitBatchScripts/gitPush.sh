#!/bin/bash
echo enter
query="git push --porcelain" 
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
$query 2>&1
# 2>&1 redirects consol output so we can read it

