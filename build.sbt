
val slidingPuzzleScalaVersion = "2.11.8"

lazy val defaultSettings = Seq(
  scalaVersion := slidingPuzzleScalaVersion,
  scalacOptions ++= Seq(
    "-deprecation", slidingPuzzleScalaVersion,
    "-unchecked",
    "-feature",
    "-encoding", "utf8"
  ),
  version := "0.2-SNAPSHOT"
)

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val javafxSettings = Seq(
  unmanagedJars in Compile += Attributed.blank(
    file(scala.util.Properties.javaHome) / "lib" / "jfxrt.jar"),
  fork in run := true
)

lazy val scalafxSettings = javafxSettings ++ Seq(
  libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"
)

lazy val root = (project in file(".")).settings(
  defaultSettings: _*
).settings(
  name := "SlidingPuzzle"
).aggregate(
  core, javafx
)

lazy val core = (project in file("core")).settings(
  (defaultSettings): _*
).settings(
  name := "SlidingPuzzle Core",
  libraryDependencies += scalaTest
)

lazy val javafx = project.in(file("javafx")).settings(
  (defaultSettings ++ scalafxSettings): _*
).settings(
  name := "SlidingPuzzle JavaFX",
  libraryDependencies += scalaTest
).dependsOn(core)

lazy val coreJs = project.settings(
  (defaultSettings): _*
).settings(
  name := "SlidingPuzzle CoreJS",
  sourceDirectory := (sourceDirectory in core).value,
  libraryDependencies += scalaTest
).enablePlugins(ScalaJSPlugin).dependsOn(core)

lazy val scalajs = project.in(file("scalajs")).settings(
  (defaultSettings): _*
).settings(
  name := "SlidingPuzzle ScalaJS",
  libraryDependencies ++= Seq(
    "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
    "org.scala-js" %%% "scalajs-dom" % "0.9.1")
).enablePlugins(ScalaJSPlugin).dependsOn(coreJs)

