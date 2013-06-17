import AssemblyKeys._ // put this at the top of the file

assemblySettings


name := "sliding-puzzle"

version := "0.1-SNAPSHOT"

organization := "com.sebnozzi"

scalaVersion := "2.10.2"


libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test")


unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))


jarName in assembly := "slidingPuzzle.jar"


mainClass in assembly := Some("com.sebnozzi.slidingpuzzle.SlidingPuzzleMain")
