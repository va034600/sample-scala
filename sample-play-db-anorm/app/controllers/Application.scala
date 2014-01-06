package controllers

import play.api._
import play.api.mvc._
import models.SampleAnorm

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(SampleAnorm.findByPk(1)))
  }

}