import com.trueaccord.scalapb.compiler.Version.scalapbVersion
import sbt._

object Dependencies {

  object Version {
    val scalaTest = "3.0.1"
    val grpcNetty = "1.2.0"
    val log4j = "2.8"
    val typesafeConfig = "1.3.1"
    val scalaFX = "8.0.102-R11"
    val akka = "2.5.0"
    val akkaHttp = "10.0.5"
  }

  lazy val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
  lazy val grpcNetty = "io.grpc" % "grpc-netty" % Version.grpcNetty
  lazy val scalaPBRuntime = "com.trueaccord.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf"
  lazy val scalaPBGRPC = "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion
  val log4jApi = "org.apache.logging.log4j" % "log4j-api" % Version.log4j
  val log4jCore = "org.apache.logging.log4j" % "log4j-core" % Version.log4j
  val log4jSlf4jImpl = "org.apache.logging.log4j" % "log4j-slf4j-impl" % Version.log4j
  val typesafeConfig = "com.typesafe" % "config" % Version.typesafeConfig
  val scalaFX = "org.scalafx" %% "scalafx" % Version.scalaFX
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
}
