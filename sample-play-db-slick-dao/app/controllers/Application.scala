package controllers

import play.api._
import play.api.mvc._
import models._
import entity._
import play.api.libs.json._

/**
 * 参考
 * slickでauto incrementを設定する
 * http://tech.noriakihoriuchi.com/slickdeauto-incrementwoshe-ding-suru-play-framework-2-1
 */
object Application extends Controller {

  def index = Action {
    implicit val sampleFormat = Json.format[Sample]
    val a = SampleModel.all()
    val json = Json.toJson(a)
    Ok(json)
  }

  def create = Action {
    val sample: Sample = Sample(None, "abcdef")
    val id = SampleModel.create(sample)
    Ok("id:" + id)
  }

}