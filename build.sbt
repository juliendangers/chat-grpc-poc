import Dependencies._

lazy val root = project.in(file("."))

lazy val `blocked-user-service` = project
  .dependsOn(domain)
  .settings(commonSettings)
  .settings(
    name := "blocked-user-service",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      log4jApi,
      log4jCore,
      log4jSlf4jImpl,
      typesafeConfig,
      scalaTest % Test
    )
  )

lazy val domain = project
  .settings(pbSettings)
  .settings(commonSettings)
  .settings(
    name := "domain",
    version := "0.0.1"
  )

lazy val pbSettings =
  Seq(
    PB.protoSources.in(Compile) := Seq(sourceDirectory.in(Compile).value / "proto"),
    PB.targets.in(Compile) := Seq(scalapb.gen() -> sourceManaged.in(Compile).value),
    libraryDependencies ++= Seq(
      scalaPBRuntime,
      scalaPBGRPC,
      grpcNetty
    )
  )

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.1",
    organization := "com.juliencrestin",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ywarn-unused-import"
    ),
    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8"
    )
  )

