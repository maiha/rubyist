name := "rubyist"

version := "0.2.3"

scalaVersion := "2.11.4"

fork in run := true

connectInput in run := true

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "commons-io" % "commons-io" % "2.4"
)

scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps")
