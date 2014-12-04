package auth

import play.api._
import play.api.mvc._
import play.api.data._
import controllers.routes
import models.AuthModel

trait HtmlSecured {

  def username(request: RequestHeader):Option[String] = {
    val sessionId = request.cookies.get("sessionId")
    println("sessionId:" + sessionId)

    if(sessionId.isEmpty){
      return None
    }

    if(AuthModel.isSessionId(sessionId.get.value)  ){
      Some(sessionId.get.value)
    }else{
      None
    }
  }

  def onUnauthorized(request: RequestHeader) = {
    var next:Call = null
    println(request.path) 
    if(request.path.startsWith("/api")){
      next = controllers.routes.AuthController.sessionErrorJson
    }else{
      next = controllers.routes.AuthController.login
    }
    Results.Redirect(next)
  }

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }
}