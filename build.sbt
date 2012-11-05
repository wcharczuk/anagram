organization := "org.inversetech"

name := "Anagram Solver"

version := "1.0.0"

scalaVersion := "2.9.2"

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"
//libraryDependencies += "org.clapper" %% "argot" % "0.4"
