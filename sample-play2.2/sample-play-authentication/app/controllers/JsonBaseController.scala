package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import auth.HtmlSecured
import auth.JsonSecured

import play.api.libs.json.JsBoolean
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller

/**
 * 参考
 * Authorization
 * http://www.playframework.com/documentation/2.0.1/ScalaSecurity
 */
class JsonBaseController extends Controller with JsonSecured {
  def sessionErrorJson = Action { implicit request =>
    val map = Map("status" -> "session_error")
    val json = Json.toJson(map)

    Ok(json)
  }

}
