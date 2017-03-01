#!/bin/bash

sed -Ef prepare-class.sed $1 | grep -v '^$' | tr '\n' ' '
echo