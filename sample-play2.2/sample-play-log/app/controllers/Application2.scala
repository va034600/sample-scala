package controllers

import play.api._
import play.api.mvc._

/**
 * 参考
 * アプリケーションのグローバル設定
 * http://www.playframework-ja.org/documentation/2.0.8/ScalaGlobal
 * アクション合成
 * http://www.playframework-ja.org/documentation/2.0.8/ScalaActionsComposition
 */
object Application2 extends Controller {
  def index2 = LoggingAction(parse.text) { request =>
    Ok("Hello World")
  }

  def LoggingAction[A](bp: BodyParser[A])(f: Request[A] => Result): Action[A] = {
    Action(bp) { request =>
      Logger.info("Calling action")
      f(request)
    }
  }
}