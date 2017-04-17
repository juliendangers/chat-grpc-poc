package com.juliencrestin.user

import com.juliencrestin.domain.user._
import org.apache.logging.log4j.LogManager

import scala.concurrent.Future

protected class BlockedUserService extends BlockedUserServiceGrpc.BlockedUserService {
  implicit val logger = LogManager.getLogger(getClass)
  val users = scala.collection.mutable.Map[String, Boolean]()

  def blockUser(request: UserRequest): Future[UserResponse] = {
    logger.info(s"block user: $request")
    users.put(request.id, true)
    Future.successful(UserResponse(request.id, true))
  }

  def isUserBlocked(request: UserRequest): Future[UserResponse] = {
    val isBlocked = users.getOrElse(request.id, false)
    logger.info(s"User ${request.id} is blocked: $isBlocked")
    Future.successful(UserResponse(request.id, isBlocked))
  }
}
