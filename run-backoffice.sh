#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECAFETERIA_CP=ecourse.app.backoffice.console/target/ecourse.app.backoffice.console-0.1.0.jar:ecourse.app.backoffice.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $ECAFETERIA_CP eapli.ecourse.app.backoffice.console.MainClass
