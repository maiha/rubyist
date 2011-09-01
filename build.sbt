organization := "sc.ala"

name := "rubyist"

version := "0.2.0"

scalaVersion := "2.9.1"

retrieveManaged := true

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.0",
  "org.specs2" %% "specs2" % "1.6-SNAPSHOT" % "test"
)

resolvers += ScalaToolsSnapshots

