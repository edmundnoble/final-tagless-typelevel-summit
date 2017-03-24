name in ThisBuild := "template"

scalaVersion in ThisBuild := "2.12.1"

scalaOrganization in ThisBuild := "org.typelevel"

version in ThisBuild := "0.0.2"

scalacOptions ++= Seq(
  "-Xlint",
  "-Ypartial-unification",
  "-Yliteral-types",
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds",
  "-Xfatal-warnings"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"

libraryDependencies += "org.atnos" %% "eff" % "3.1.0"

resolvers += Resolver.sonatypeRepo("public")

javacOptions += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

fork in test := true
