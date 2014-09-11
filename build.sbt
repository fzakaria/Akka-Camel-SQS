name := "SQS Akka Camel"
 
version := "1.0"
 
scalaVersion := "2.10.4"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
 
libraryDependencies ++= Seq(
  "org.apache.camel" % "camel-aws" % "2.13.2",
  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT",
  "com.typesafe.akka" %% "akka-camel" % "2.4-SNAPSHOT",
  "com.amazonaws" % "aws-java-sdk" % "1.8.9.1",
  "org.slf4j" % "slf4j-api" % "1.7.2",
  "ch.qos.logback" % "logback-classic" % "1.0.7"
  )
