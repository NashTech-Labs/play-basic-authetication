package controllers

import javax.inject._
import models.UserRepository
import play.api._
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class HomeController @Inject()(userRepository: UserRepository) extends Controller {
  def index = Action { implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }

  def indexWithAuthentication =
    BasicAuthentication(userRepository.findUser) {
      Action { implicit request =>
        Ok("Authentication Successful")
      }
    }
}
