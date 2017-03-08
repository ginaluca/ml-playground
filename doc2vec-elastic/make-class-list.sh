#!/usr/bin/env bash

find $1 -name "*.java" -exec basename -s .java {} \; | sort -u > classes.txt