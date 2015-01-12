package controllers

import play.api._
import play.api.mvc._
import org.pac4j.play.scala.ScalaController
import org.pac4j.oauth.profile.twitter.TwitterProfile
import org.pac4j.core.profile.CommonProfile

/**
 * https://github.com/pac4j/play-pac4j-scala-demo
 */
object Application extends ScalaController {

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val urlTwitter = getRedirectAction(request, newSession, "TwitterClient", "/twitter/index.html").getLocation()
    val urlGoogle = getRedirectAction(request, newSession, "Google2Client", "/google/index.html").getLocation()
    Ok(views.html.index(urlTwitter, urlGoogle)).withSession(newSession)
  }

  def twitterIndex = RequiresAuthentication("TwitterClient") { profile =>
    Action { request =>
      Ok(views.html.protectedIndex(profile))
    }
  }

  def googleIndex = RequiresAuthentication("Google2Client") { profile =>
    Action { request =>
      Ok(views.html.protectedIndex(profile))
    }
  }
}