import sbt._
import sbt.Keys._

object SlidingpuzzleBuild extends Build {

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"


  lazy val slidingpuzzle = Project(
    id = "slidingpuzzle",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "SlidingPuzzle",
      organization := "com.sebnozzi",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.1",
      libraryDependencies ++= Seq(
      	scalaTest
      )
      // add other settings here
    )
  )
}
