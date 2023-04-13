#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECAFETERIA_CP=ecourse.app.user/target/ecourse.app.user-0.1.0.jar:ecourse.app.user/target/dependency/*;


#REM call the java VM, e.g,
java -cp $ECAFETERIA_CP org.user.Main
