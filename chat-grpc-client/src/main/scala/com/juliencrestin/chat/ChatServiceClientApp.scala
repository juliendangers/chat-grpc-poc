package com.juliencrestin.chat

import com.juliencrestin.domain.chat.ChatMessage.ChatMessageType

import scalafx.application.{JFXApp, Platform}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import com.juliencrestin.domain.chat.{ChatMessage, ChatServiceGrpc}
import com.typesafe.config.ConfigFactory
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import org.apache.logging.log4j.LogManager

import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{Button, ListView, TextField}

object ChatServiceClientApp extends JFXApp {

  implicit val logger = LogManager.getLogger(getClass)

  /******************
   *    SETTINGS    *
   ******************/
  val config = ConfigFactory.load()
  val Host = config.getString("app.server.host")
  val Port = config.getInt("app.server.port")

  /******************
   *       UI       *
   ******************/
  val messages = ObservableBuffer[String]()
  val nameField = new TextField {
    text = "name"
  }
  val textField = new TextField()
  val h = 450
  val w = 385

  stage = new PrimaryStage {
    alwaysOnTop = true
    resizable = false
    title = "Chat application"
    height = h
    width = w
    scene = new Scene(w, 430) {
      content = new BorderPane {
        center = new ListView[String]() {
          items = messages
        }
        bottom = new BorderPane {
          left = nameField
          center = textField
          right = new Button {
            text = "Send"
            onAction = (e) => {
              textField.text.value.trim match {
                case "" =>
                case text => chatToServer.onNext(
                    ChatMessage(nameField.text.value, text, ChatMessageType.CHAT)
                  )
                  textField.text.value = ""
              }
              textField.requestFocus()
            }
          }
        }
      }
    }
  }

  /*********************
   *    GRPC client    *
   *********************/
  val channel = ManagedChannelBuilder.forAddress(Host, Port).usePlaintext(true).build
  val chatClient = ChatServiceGrpc.stub(channel)
  val chatToServer = chatClient.chat(new StreamObserver[ChatMessage] {
    override def onError(t: Throwable): Unit = {
      logger.error(s"Got error ${t.getMessage}")
      stage.close()
    }

    override def onCompleted(): Unit = {
      logger.info(s"Chat ended")
    }

    override def onNext(message: ChatMessage): Unit = {
      Platform.runLater(
        messages.add(s"${message.name}: ${message.content}")
      )
    }
  })

  // Set focus on name, and select text
  nameField.requestFocus()
  nameField.selectAll()
}
