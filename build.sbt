
val slidingPuzzleScalaVersion = "2.13.2"

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

val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % "test"

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

// === JavaFX ===

lazy val javafxSettings = Seq(
    fork := true
  )

lazy val javafx = project.in(file("javafx")).settings(
  (defaultSettings ++ scalafxSettings): _*
).settings(
  name := "SlidingPuzzle JavaFX",
  libraryDependencies += scalaTest
).dependsOn(core)

lazy val scalafxSettings = {
  // Add dependency on JavaFX libraries, OS dependent
  lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")

  // Determine OS version of JavaFX binaries
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
  }

  javafxSettings ++ Seq(
    libraryDependencies ++= javaFXModules.map( m =>
      "org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName
    ),
    libraryDependencies += "org.scalafx" %% "scalafx" % "14-R19"
  )
}

// === Scala JS ===

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
    "be.doeraene" %%% "scalajs-jquery" % "1.0.0",
    "org.scala-js" %%% "scalajs-dom" % "1.0.0")
).enablePlugins(ScalaJSPlugin).dependsOn(coreJs)

