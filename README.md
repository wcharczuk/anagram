##Anagram Solver

This program (written in scala) leverages the letterpress dictionary to permute valid words based on an input word (or set of characters).
There is a pre-built version of the program, but if you want to tinker and run the code yourself all you need is scala and SBT >0.12

######To use the pre-built jar, download the jar and the wrapping shell script in __release/__

To run code directly:

```bash
>> sbt 
> run [Normal Arguments Here]
```

Typical Arguments:

* `-i ove` : include `ove` in all output words (i.e. output words must have these letters)
* `-m 5` : words should have a minimum of 5 letters
* `-r 10` : only return the 10 longest words
* `oilhderard` : the pool of letters to permute words from;

`run -i ove -m 5 -r 10 oilhderard` would be the resulting command. 

To compile into a jar:

> sbt one-jar 

then the jar will be in /target/scala-2.9.2/***-one-jar.jar
