package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

/**
 * 参考
 * Authorization
 * http://www.playframework.com/documentation/2.0.1/ScalaSecurity
 */
object Application extends BaseController {
  def index = withAuth { username =>
    implicit request =>
      Ok(views.html.index(username))
  }
}
