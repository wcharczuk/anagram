##Anagram Solver

This program (written in scala) leverages the letterpress dictionary to permute valid words based on an input word (or set of characters).
There is a pre-built version of the program, but if you want to tinker and run the code yourself all you need is scala and SBT >0.12

######To use the pre-built jar, download the jar and the wrapping shell script in __release/__

To run code directly:

> sbt "run [Normal Arguments Here]"

To compile into a jar:

> sbt one-jar 

then the jar will be in /target/scala-2.9.2/***-one-jar.jar
