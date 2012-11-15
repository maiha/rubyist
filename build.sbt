organization := "sc.ala"

name := "rubyist"

version := "0.2.1"

scalaVersion := "2.9.2"

retrieveManaged := true

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.1" % "test"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.0",
  "org.specs2" %% "specs2" % "1.9" % "test"
)

resolvers += ScalaToolsSnapshots

