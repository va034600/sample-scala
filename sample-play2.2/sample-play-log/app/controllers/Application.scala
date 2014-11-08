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
object Application extends BaseApplication {
  def index = LoggingAction { request =>
    Ok(views.html.index(loginForm))
  }

  def submit = LoggingAction { implicit request =>
    Ok(views.html.index(loginForm))
  }

 val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text))
}