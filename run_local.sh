#!/bin/bash

sbt "~run -Drun.mode=Dev -Dhttp.port=15506 -Dapplication.router=testOnlyDoNotUseInAppConf.Routes $*"
