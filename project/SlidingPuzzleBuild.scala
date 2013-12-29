import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

import scala.scalajs.sbtplugin._
import ScalaJSPlugin._
import ScalaJSKeys._

import ScctPlugin.instrumentSettings



object SlidingPuzzleBuild extends Build {

  val slidingPuzzleScalaVersion = "2.10.3"

  val defaultSettings: Seq[Setting[_]] = Seq(
      scalaVersion := slidingPuzzleScalaVersion,
      scalacOptions ++= Seq(
          "-deprecation",slidingPuzzleScalaVersion,
          "-unchecked",
          "-feature",
          "-encoding", "utf8"
      ),
      version := "0.1-SNAPSHOT"
  )

  val javafxSettings: Seq[Setting[_]] = Seq(
      unmanagedJars in Compile += Attributed.blank(
          file(scala.util.Properties.javaHome) / "lib" / "jfxrt.jar"),
      fork in run := true
  ) ++ Seq(assemblySettings:_*)

  val scalafxSettings: Seq[Setting[_]] = javafxSettings ++ Seq(
      libraryDependencies += "org.scalafx" %% "scalafx" % "1.0.0-M4"
  )

  lazy val root = project.in(file(".")).settings(
      defaultSettings: _*
  ).settings(
      name := "SlidingPuzzle"
  ).settings(
      ScctPlugin.mergeReportSettings: _*
  ).aggregate(
      core, javafx
  )

  lazy val core = project.in(file("core")).settings(
      (defaultSettings): _*
  ).settings(
      name := "SlidingPuzzle Core",
      libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  ).settings(ScctPlugin.instrumentSettings: _*) 
  
  lazy val javafx = project.in(file("javafx")).settings(
      (defaultSettings ++ scalafxSettings): _*
  ).settings(
      name := "SlidingPuzzle JavaFX",
      libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  ).settings(
    jarName in assembly := "slidingPuzzle.jar"
  ).dependsOn(core)

  lazy val coreJs = project.settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
    name := "SlidingPuzzle CoreJS",
    sourceDirectory := (sourceDirectory in core).value,
    libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  ).dependsOn(core)
  
  lazy val scalajs = project.in(file("scalajs")).settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
      name := "SlidingPuzzle ScalaJS",
      libraryDependencies += "org.scala-lang.modules.scalajs" %% "scalajs-jquery" % "0.1-SNAPSHOT",
      libraryDependencies += "org.scala-lang.modules.scalajs" %% "scalajs-dom" % "0.1-SNAPSHOT",
      // Add the startup.js file of this project
      unmanagedSources in (Compile, packageJS) ++= Seq(
        baseDirectory.value / "resources" / "js" / "startup.js")
  ).dependsOn(coreJs)  

}
