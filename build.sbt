name := "ScaTiTralI"

version := "1.0"

scalaVersion := "2.10.5"

scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

scalacOptions in (Compile, doc) ++= Seq("-doc-title", "ScaTiTralI")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

resolvers += Classpaths.typesafeResolver

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += Resolver.sonatypeRepo("public")


resolvers += Resolver.typesafeRepo("releases")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

