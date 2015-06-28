import sbt.Keys._

scalaVersion := "2.11.7"
fork in run := true
fork in Test := true
connectInput in run := true

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.7", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

xerial.sbt.Sonatype.sonatypeRootSettings

// Maven Publishing
// http://www.scala-sbt.org/0.13/docs/Using-Sonatype.html

publishMavenStyle := true
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

version := "0.2.6"
organization := "sc.ala"
name := "rubyist"
description := "A scala goodies for rubyist"
homepage := Some(url("https://github.com/maiha/rubyist"))
licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

pomExtra := (
     <developers>
        <developer>
          <id>maiha</id>
          <name>Kazunori Nishi</name>
          <url>https://github.com/maiha</url>
        </developer>
      </developers>
      <scm>
        <url>https://github.com/maiha/rubyist</url>
        <connection>scm:git:git@github.com:maiha/rubyist.git</connection>
      </scm>
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
)
