name := """manytomany"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file("."))
 .enablePlugins(PlayJava, PlayEbean)  
scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1100-jdbc41" ;
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.0.6"
