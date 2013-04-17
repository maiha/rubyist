organization := "sc.ala"

name := "rubyist"

version := "0.2.2"

scalaVersion := "2.10.1"

// https://github.com/sbt/sbt/issues/709
conflictWarning in ThisBuild ~= { _.copy(level = Level.Debug) }

// re-run with -feature for details
scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps")

retrieveManaged := true

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.0",
  "org.specs2" %% "specs2" % "1.14" % "test"
)

resolvers += ScalaToolsSnapshots

