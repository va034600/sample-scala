package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import auth.HtmlSecured

/**
 * 参考
 * Authorization
 * http://www.playframework.com/documentation/2.0.1/ScalaSecurity
 */
class BaseController extends Controller with HtmlSecured {
  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text) verifying ("Invalid email or password", result => result match {
        case (email, password) => check(email, password)
      }))

  def check(username: String, password: String) = {
    (username == "admin" && password == "1234")
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      user => Redirect(routes.Application.index).withSession(Security.username -> user._1))
  }

  def logout = Action {
    Redirect(routes.Application.login).withNewSession.flashing(
      "success" -> "You are now logged out.")
  }

  def sessionErrorJson = Action { implicit request =>
    val map = Map("status" -> "session_error")
    val json = Json.toJson(map)

    Ok(json)
  }

}
