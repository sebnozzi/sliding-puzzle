name := "sliding-puzzle"

version := "0.1-SNAPSHOT"

organization := "com.sebnozzi"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
)


unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))
