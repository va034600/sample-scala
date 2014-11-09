package models

import play.api.db.slick.Config.driver.simple._
import play.api.Play.current
import play.api.db.slick._
import play.api.libs.json._

case class Sample(id: Long, name: String)

object Samples extends Table[Sample]("sample") {
  implicit val sampleFormat = Json.format[Sample]  

  def id = column[Long]("id", O.PrimaryKey)
  def name = column[String]("name", O.NotNull)

  def * = id ~ name <> (Sample.apply _, Sample.unapply _)
  
  def all():JsValue = DB.withSession{ implicit session:Session =>
    val a = Query(Samples).sortBy(_.id).list
    Json.toJson(a)
  }
}