package com.juliencrestin.user

import com.juliencrestin.domain.user._
import com.typesafe.config.ConfigFactory
import io.grpc.ManagedChannelBuilder
import org.apache.logging.log4j.LogManager

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object BlockedUserClientApp {

  implicit val logger = LogManager.getLogger(getClass)

  private val config  = ConfigFactory.load()
  private val Host    = config.getString("app.server.host")
  private val Port    = config.getInt("app.server.port")
  private val Secured = config.getBoolean("app.server.secured")

  private val channel = ManagedChannelBuilder.forAddress(Host, Port).usePlaintext(Secured).build
  private val request = UserRequest("test")

  def main(args: Array[String]): Unit = {
    // create an asynchronous stub
    val blockedUserClient = BlockedUserServiceGrpc.stub(channel)

    // block user test
    blockedUserClient
      .blockUser(request)
      .onComplete {
        case Success(userResponse) => logger.info(s"[async client] received response: $userResponse")
        case Failure(e) => logger.info(s"[async client] error while calling blocked user service: $e")
      }

    while (true) {
      Console.out.println("User id:")
      val id  = scala.io.StdIn.readLine()
      blockedUserClient
        .isUserBlocked(UserRequest(id))
        .onComplete {
          case Success(userResponse) => logger.info(s"[async client] received response: $userResponse")
          case Failure(e) => logger.info(s"[async client] error while calling blocked user service: $e")
        }
    }
  }

}
