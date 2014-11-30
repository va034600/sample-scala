package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import models.AuthModel

/**
 * 参考
 * Authorization
 * http://www.playframework.com/documentation/2.0.1/ScalaSecurity
 */
object BaseController extends Controller {
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
      user => {
        //sessionid作成
        val sessionId = java.util.UUID.randomUUID().toString()

        //sessionId保存
        AuthModel.saveSessionId(sessionId)

        Redirect(controllers.routes.Application.index).withCookies(
        Cookie("sessionId", sessionId, Some(3600 * 24 * 7)))
      }
    )
  }

  def logout = Action {implicit request =>
    val sessionId = request.cookies.get("sessionId")
    if(sessionId.isDefined){
      AuthModel.removeSession(sessionId.get.value)
    }
    Redirect(controllers.routes.BaseController.login).discardingCookies(DiscardingCookie("sessionId"))
  }

  def sessionErrorJson = Action { implicit request =>
    val map = Map("status" -> "session_error")
    val json = Json.toJson(map)

    Ok(json)
  }

}
