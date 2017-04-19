package com.juliencrestin.whatsapp

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory
import org.apache.logging.log4j.LogManager

object Server extends App {
  implicit val logger = LogManager.getLogger(getClass)

  implicit val actorSystem = ActorSystem()
  implicit val executionContext = actorSystem.dispatcher
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()
  val port   = config.getInt("app.server.port")

  def getAbsolutePath(relativePath: String) = {
    val baseDir = Seq(System.getProperty("user.dir"), "whatsapp-grpc-client")
    (baseDir ++ relativePath.split("/")).mkString(java.io.File.separator)
  }

  // serving static files
  val route =
    pathPrefix("public") {
      get {
        getFromDirectory(getAbsolutePath("/public"))
      }
    } ~
    pathPrefix("node_modules") {
      get {
        getFromDirectory(getAbsolutePath("/node_modules"))
      }
    } ~
    pathEndOrSingleSlash {
      get {
        getFromFile(getAbsolutePath("/public/index.html"))
      }
    }

  Http(actorSystem)
    .bindAndHandle(handler = route, interface = "0.0.0.0", port = port)

  logger.info("Server starter on port $port")
}
