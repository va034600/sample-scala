package auth

import play.api._
import play.api.mvc._
import play.api.data._
import controllers.routes

trait JsonSecured {

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(controllers.routes.JsonController.sessionErrorJson)

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }
}