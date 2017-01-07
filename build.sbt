name := "multi-agent-particles"
version := "1.0"
scalaVersion := "2.12.1"
organization := "fil.iagl.idl.sma"
mainClass in assembly := Some("fil.iagl.idl.sma.Main")
assemblyJarName in assembly := "sma.jar"

val scalafxVersion = "8.0.102-R11"
val clistVersion = "3.2.2"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % scalafxVersion,
  "org.backuity.clist" %% "clist-core"   % clistVersion,
  "org.backuity.clist" %% "clist-macros" % clistVersion % "provided"
)

