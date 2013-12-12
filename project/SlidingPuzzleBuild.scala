import sbt._
import Keys._

import scala.scalajs.sbtplugin._
import ScalaJSPlugin._
import ScalaJSKeys._

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
  )

  val scalafxSettings: Seq[Setting[_]] = javafxSettings ++ Seq(
      libraryDependencies += "org.scalafx" %% "scalafx" % "1.0.0-M4"
  )

  lazy val root = project.in(file(".")).settings(
      defaultSettings: _*
  ).settings(
      name := "SlidingPuzzle"
  ).aggregate(
      core, javafx
  )

  lazy val core = project.in(file("core")).settings(
      (defaultSettings): _*
  ).settings(
      name := "SlidingPuzzle Core",
      libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  )  
  
  lazy val javafx = project.in(file("javafx")).settings(
      (defaultSettings ++ scalafxSettings): _*
  ).settings(
      name := "SlidingPuzzle JavaFX"
  ).dependsOn(core)

  lazy val coreJs = project.settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
    name := "SlidingPuzzle CoreJS",
      sourceDirectory := (sourceDirectory in core).value
  ).dependsOn(core)
  
  lazy val scalajs = project.in(file("scalajs")).settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
      name := "SlidingPuzzle ScalaJS",
      // Add the startup.js file of this example project
      unmanagedSources in (Compile, packageJS) +=
        baseDirectory.value / "startup.js"
  ).dependsOn(coreJs)  
  
  /*

  lazy val slidingPuzzleJs = project.settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
      name := "SlidingPuzzle JS",
      sourceDirectory := (sourceDirectory in mazes).value
  ).dependsOn(core)

  lazy val slidingPuzzleFx = project.in(file("javafx")).settings(
      (defaultSettings ++ javafxSettings): _*
  ).settings(
      name := "JavaFX-based graphics"
  ).dependsOn(core)

  lazy val html5Graphics = project.in(file("html5-graphics")).settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
      name := "HTML5-based graphics"
  ).dependsOn(corejs)

  lazy val runner = project.settings(
      (defaultSettings ++ scalafxSettings): _*
  ).settings(
      name := "FunLabyrinthe runner",
      mainClass := Some("com.funlabyrinthe.runner.Main")
  ).dependsOn(core, mazes, javafxGraphics)

  lazy val runnerjs = project.settings(
      (defaultSettings ++ scalaJSSettings): _*
  ).settings(
      name := "FunLabyrinthe runner js",
      unmanagedSources in (Compile, packageJS) +=
        baseDirectory.value / "js" / "startup.js"
  ).dependsOn(corejs, mazesjs, html5Graphics)
  */

}
