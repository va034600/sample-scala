package auth

import play.api._
import play.api.mvc._
import play.api.data._
import controllers.routes

trait HtmlSecured {

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = {
    var next:Call = null
    println(request.path) 
    if(request.path.startsWith("/api")){
      next = controllers.routes.JsonController.sessionErrorJson
    }else{
      next = controllers.routes.Application.login
    }
    Results.Redirect(next)
  }

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }
}