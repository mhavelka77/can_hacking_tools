#!/bin/bash

while read var
	do cansend $1 $var
done

