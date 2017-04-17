package com.juliencrestin.chat

import io.grpc.stub.StreamObserver

import scala.collection.mutable
import com.juliencrestin.domain.chat._
import org.apache.logging.log4j.LogManager

class ChatService extends ChatServiceGrpc.ChatService {

  implicit val logger = LogManager.getLogger(getClass)

  def chat(responseObserver: StreamObserver[ChatMessage]): StreamObserver[ChatMessage] = {
    ChatService.observers.add(responseObserver)
    new StreamObserver[ChatMessage]() {
      override def onNext(message: ChatMessage): Unit = {
        logger.info(s"Got message from ${message.name}: ${message.content}")
        // forward error to emitter only
        message.`type` match {
          case ChatMessage.ChatMessageType.CHAT => ChatService.observers.foreach(_.onNext(message))
          case _ => responseObserver.onNext(message)
        }
      }

      override def onError(t: Throwable): Unit = {
        logger.error(s"An error occurred ${t.getMessage}")
        ChatService.observers.remove(responseObserver)
      }

      override def onCompleted(): Unit = {
        logger.debug("Stream ended")
        ChatService.observers.remove(responseObserver)
      }
    }
  }
}

object ChatService {
  // list of clients' observer
  val observers = mutable.LinkedHashSet[StreamObserver[ChatMessage]]()
}