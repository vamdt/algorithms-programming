#!/usr/bin/env bash

# enter current dir
cd $(dirname $0)
# get parent dir path
PROJECT_PATH=$(dirname $(pwd))
# set src path
SRC_PATH=$PROJECT_PATH/src
# set tmp path
TMP_PATH=$PROJECT_PATH/tmp

# make tmp path if path not exists
if [ ! -d $TMP_PATH ]; then
    mkdir $TMP_PATH
fi

# pack three java files
rm -f $TMP_PATH/collinear.zip
zip -j $TMP_PATH/collinear.zip $SRC_PATH/Point.java $SRC_PATH/Brute.java $SRC_PATH/Fast.java

