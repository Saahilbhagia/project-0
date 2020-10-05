del /s *.class
javac -cp ".;lib\*" -sourcepath . test/Project0Evaluation.java
if ERRORLEVEL 1 goto End

java -cp ".;lib\*" test.Project0Evaluation
:End
