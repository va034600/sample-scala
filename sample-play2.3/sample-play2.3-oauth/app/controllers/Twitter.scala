package controllers

import play.api._
import play.api.mvc._
import play.api.libs.oauth._
import java.util.ResourceBundle

/**
 * https://www.playframework.com/documentation/2.0/ScalaOAuth
 */
object Twitter extends Controller {

  val TWITTER = OAuth(ServiceInfo(
    "https://api.twitter.com/oauth/request_token",
    "https://api.twitter.com/oauth/access_token",
    "https://api.twitter.com/oauth/authorize", getKey),
    false)

    def getKey:ConsumerKey = {
      val resourceBundle = ResourceBundle.getBundle("application")
      ConsumerKey(resourceBundle.getString("twitter_api_key"), resourceBundle.getString("twitter_api_secret"))
  }

  def redirect = Action { request =>
      println("aa")
      TWITTER.retrieveRequestToken("http://127.0.0.1:9000/twitter/auth") match {
        case Right(t) => {
          Redirect(TWITTER.redirectUrl(t.token)).withSession("token" -> t.token, "secret" -> t.secret)
        }
        case Left(e) => throw e
      }
  }

  def authenticate(oauth_token: String, oauth_verifier: String) = Action { request =>
      println("bb")
      val tokenPair = sessionTokenPair(request).get
      TWITTER.retrieveAccessToken(tokenPair, oauth_verifier) match {
        case Right(t) => {
          Redirect(routes.Twitter.end).withSession("token" -> t.token, "secret" -> t.secret)
        }
        case Left(e) => throw e
      }
  }

  def sessionTokenPair(implicit request: RequestHeader): Option[RequestToken] = {
    for {
      token <- request.session.get("token")
      secret <- request.session.get("secret")
    } yield {
      RequestToken(token, secret)
    }
  }

  def end = Action {implicit session =>
    Ok(views.html.end())
  }
}