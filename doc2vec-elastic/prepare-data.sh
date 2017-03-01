#!/bin/bash

find $1 -name "*.java" -exec ./prepare-class.sh {} \;
