#!/bin/bash
set -e

find . -type f -name '*.class' -delete
javac -cp *:lib/*:. -sourcepath . test/Project0Evaluation.java
java -cp *:lib/*:. test.Project0Evaluation

