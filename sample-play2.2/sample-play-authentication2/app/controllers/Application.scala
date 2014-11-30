package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import auth.HtmlSecured

/**
 * 参考
 * Authorization
 * http://www.playframework.com/documentation/2.0.1/ScalaSecurity
 */
object Application extends Controller with HtmlSecured {
  def index = withAuth { username =>
    implicit request =>
      Ok(views.html.index(username))
  }
}
