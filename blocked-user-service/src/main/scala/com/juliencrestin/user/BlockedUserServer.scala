package com.juliencrestin.user

import com.juliencrestin.domain.user._
import com.typesafe.config.ConfigFactory
import io.grpc.ServerBuilder
import org.apache.logging.log4j.LogManager

import scala.concurrent.ExecutionContext

object BlockedUserServer {

  implicit val logger = LogManager.getLogger(getClass)
  private val config = ConfigFactory.load()
  private val port   = config.getInt("app.server.port")

  def main(args: Array[String]): Unit = {
    val serverServiceDefinition = BlockedUserServiceGrpc.bindService(new BlockedUserService, ExecutionContext.global)
    val server = ServerBuilder
      .forPort(port)
      .addService(serverServiceDefinition)
      .build
    server.start
    logger.info(s"Server started. Listening on port $port")
    server.awaitTermination
  }

}