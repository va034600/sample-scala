package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

class BaseApplication extends Controller {
  def LoggingAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action { implicit request =>
      var a = request.body
      var d = a.asFormUrlEncoded
      println(d)
      f(request)
    }
  }
}