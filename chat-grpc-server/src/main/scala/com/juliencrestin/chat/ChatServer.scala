package com.juliencrestin.chat

import com.juliencrestin.domain.chat.ChatServiceGrpc
import com.typesafe.config.ConfigFactory
import io.grpc.ServerBuilder
import org.apache.logging.log4j.LogManager

import scala.concurrent.ExecutionContext

object ChatServer extends App {

  implicit val logger = LogManager.getLogger(getClass)

  val config = ConfigFactory.load()
  val serverPort = config.getInt("app.server.port")

  val serverServiceDefinition = ChatServiceGrpc.bindService(new ChatService, ExecutionContext.global)
  val server = ServerBuilder
    .forPort(serverPort)
    .addService(serverServiceDefinition)
    .build
  server.start
  logger.info(s"Server started. Listening on port $serverPort")
  server.awaitTermination

}
