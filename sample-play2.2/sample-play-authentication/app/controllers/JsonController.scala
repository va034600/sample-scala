package controllers

import scala.math.BigDecimal.int2bigDecimal

import play.api.libs.json.JsBoolean
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller

object JsonController extends BaseController {
  def json1 = withAuth { username =>
    implicit request =>
    val map = Map("aaa" -> "bbb")	
    val json = Json.toJson(map)
    Ok(json)
  }
}