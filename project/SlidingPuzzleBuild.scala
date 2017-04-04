import sbt._
import Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._


object SlidingPuzzleBuild extends Build {

  val slidingPuzzleScalaVersion = "2.11.8"

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
      libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"
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
      name := "SlidingPuzzle JavaFX",
      libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  ).dependsOn(core)

  lazy val coreJs = project.settings(
      (defaultSettings): _*
  ).settings(
    name := "SlidingPuzzle CoreJS",
    sourceDirectory := (sourceDirectory in core).value,
    libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  ).dependsOn(core).enablePlugins(ScalaJSPlugin)

  lazy val scalajs = project.in(file("scalajs")).settings(
      (defaultSettings): _*
  ).settings(
      name := "SlidingPuzzle ScalaJS",
      libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
      libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1",
      // Add the startup.js file of this project
      unmanagedSources in (Compile, fastOptJS) ++= Seq(
        baseDirectory.value / "resources" / "js" / "startup.js")
  ).dependsOn(coreJs).enablePlugins(ScalaJSPlugin)

}
