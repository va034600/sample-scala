package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

/**
 * 参考
 * アプリケーションのグローバル設定
 * http://www.playframework-ja.org/documentation/2.0.8/ScalaGlobal
 * アクション合成
 * http://www.playframework-ja.org/documentation/2.0.8/ScalaActionsComposition
 */
object Application extends Controller {
  def index = LoggingAction { request =>
    Ok(views.html.index(loginForm))
  }

  def LoggingAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action { request =>
      Logger.info("Calling action")
      f(request)
    }
  }

  def submit = Action { implicit request =>
    Ok(views.html.index(loginForm))
  }

 val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text))
}