lazy val root = (project in file("."))
  .settings(
    name := "PokerKata",
    scalaVersion := "2.12.2"
)

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.10",
  "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6",
  "org.scalatest" % "scalatest_2.12" % "3.0.1",
  "org.scalactic" % "scalactic_2.12" % "3.0.1"
)
