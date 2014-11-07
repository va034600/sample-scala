package controllers

import play.api.mvc.Action
import play.api.mvc.Controller

object Application extends Controller {   
  def index = Action {
    val json = Samples.all()
    Ok(json)
  }
}