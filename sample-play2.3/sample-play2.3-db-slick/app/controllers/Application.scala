package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.libs.json.Json
import models.SampleDao
import models.Sample

object Application extends Controller {   
  implicit val sampleFormat = Json.format[Sample]  

  def index = Action {
    val list = SampleDao.all
    val json = Json.toJson(list)
    Ok(json)
  }
}