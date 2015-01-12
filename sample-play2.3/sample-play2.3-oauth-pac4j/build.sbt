name := """sample-play2.3-oauth-pac4j"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

resolvers ++= Seq(
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "org.pac4j" % "play-pac4j_scala2.11" % "1.3.0",
  "org.pac4j" % "pac4j-oauth" % "1.6.0"
)