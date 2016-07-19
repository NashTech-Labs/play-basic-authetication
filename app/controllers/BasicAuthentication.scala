package controllers

import org.apache.commons.codec.binary.Base64.decodeBase64
import play.api.mvc.{Controller, Action}

import scala.concurrent.Future
import scala.util.control.Exception.allCatch

object BasicAuthentication extends Controller {
  def apply[A](userExists: (String, String) => Boolean)(action: Action[A]): Action[A] =
    Action.async(action.parser) { request =>
      request.headers.get("Authorization").flatMap { authorization =>
        authorization.split(" ").drop(1).headOption.filter { encoded =>
          val authInfo = new String(decodeBase64(encoded.getBytes)).split(":").toList

          allCatch.opt {
            val (username, password) = (authInfo.head, authInfo(1))

            userExists(username, password)
          } getOrElse false
        }
      }.map(_ => action(request)).getOrElse {
        Future.successful(Unauthorized("Authentication Failed"))
      }
    }
}
