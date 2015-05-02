name := """play-deadbolt2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

EclipseKeys.createSrc := EclipseCreateSrc.All

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "be.objectify" %% "deadbolt-scala" % "2.3.2",
  "org.reactivemongo" % "reactivemongo_2.11" % "0.10.5.0.akka23",
  "org.reactivemongo" % "reactivemongo-extensions-json_2.11" % "0.10.5.0.0.akka23",
  "se.radley" %% "play-plugins-salat" % "1.5.0"
)


fork in run := true