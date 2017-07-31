import sbt._
import Keys._

object CommSettings {

  val appVersion = "0.0.1"

  val settings: Seq[Def.Setting[_]] = Seq(
    version := appVersion,
    scalaVersion := "2.11.7",
    javacOptions ++= Seq("-source", "1.7", "-target", "1.7"), //, "-Xmx2G"),
    scalacOptions ++= Seq("-deprecation", "-unchecked")
  )
}
