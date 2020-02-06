#!/bin/bash
cp run-tests-in-the-cloud.sh run-tests.sh
zip -r server_side_test_package pom.xml run-tests-in-cloud.sh src
rm run-tests.sh