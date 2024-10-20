#! /bin/bash

for i in *;
do pushd $i;
	if [ -f "pom.xml" ];
	then
		mvn clean
	fi;
popd;
done;

