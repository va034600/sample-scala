package auth

import play.api._
import play.api.mvc._
import play.api.data._
import controllers.routes
import play.api.cache._
import play.api.Play.current;

trait HtmlSecured {

  def username(request: RequestHeader):Option[String] = {
    val sessionId = request.cookies.get("sessionId")
    println("sessionId:" + sessionId)

    if(sessionId.isEmpty){
      return None
    }

    if(Cache.get("sessionId").isEmpty){
      return None
    }

    if(Cache.get("sessionId").get == sessionId.get.value ){
      println("ok:" + Cache.get("sessionId"))
      Some(sessionId.toString())
    }else{
      println("ng:" + Cache.get("sessionId"))
      None
    }
  }

  def onUnauthorized(request: RequestHeader) = {
    var next:Call = null
    println(request.path) 
    if(request.path.startsWith("/api")){
      next = controllers.routes.BaseController.sessionErrorJson
    }else{
      next = controllers.routes.BaseController.login
    }
    Results.Redirect(next)
  }

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }
}