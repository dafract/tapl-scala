val scala3Version = "3.0.0"

lazy val root = project
    .in(file("."))
    .settings(
      name := "tapl-scala",
      version := "0.1.0",

      scalaVersion := scala3Version,

      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",

      Test / parallelExecution := false
    )
