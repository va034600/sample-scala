name := "sample-play-db-slick"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
      "com.typesafe.slick" %% "slick" % "1.0.1",
      "com.typesafe.play" %% "play-slick" % "0.5.0.8"
)     

play.Project.playScalaSettings
