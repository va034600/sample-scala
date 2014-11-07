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
object Application extends Controller {
  def index = LoggingAction { request =>
    Ok(views.html.index("Your new application is ready."))
  }

  def LoggingAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action { request =>
      Logger.info("Calling action")
      f(request)
    }
  }
}