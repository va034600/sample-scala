package auth

import play.api._
import play.api.mvc._
import play.api.data._
import controllers.routes
import models.AuthModel

trait HtmlSecured {

  def checkSession(request: RequestHeader):Option[String] = {
    val sessionId = request.cookies.get("sessionId")
    println("sessionId:" + sessionId)

    if(sessionId.isEmpty){
      return None
    }

    AuthModel.getEmail(sessionId.get.value)
  }

  def onUnauthorized(request: RequestHeader) = {
    var next:Call = null
    println("path:" + request.path) 
    if(request.path.startsWith("/api")){
      next = controllers.routes.AuthController.sessionErrorJson
    }else{
      next = controllers.routes.AuthController.login
    }
    Results.Redirect(next)
  }

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(checkSession, onUnauthorized) { email =>
      Action(request => f(email)(request))
    }
  }
}