name := "rubyist"

version := "0.2.4"

scalaVersion := "2.11.4"

fork in run := true

connectInput in run := true

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "commons-io" % "commons-io" % "2.4"
)

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-language:implicitConversions", "-language:postfixOps")

