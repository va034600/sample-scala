package controllers

import play.api._
import play.api.mvc._
import play.api.cache._

/**
 * 参考
 * http://xawa99.blogspot.jp/2013/06/Scala-Play-Session.html
 */
object Application extends Controller {

  def index = Action.apply { request =>
    val abc: String = request.session.get("abc").getOrElse("")
    Ok("abc:" + abc).withSession(
      "abc" -> "def");
  }

  def index2 = Action.apply { request =>
    var sessionId: String = request.session.get("sid") match {
      case Some(x) => x;
      case None => java.util.UUID.randomUUID().toString();
    }

    import play.api.Play.current;
    var value: String = Cache.get(sessionId) match {
      case Some(x) => Cache.set(sessionId, x, 10); x.asInstanceOf[String];
      case None => Cache.set(sessionId, "def", 10); "";
    }

    Ok("value = " + value).withSession(
      request.session + ("sid" -> sessionId));
  }
}