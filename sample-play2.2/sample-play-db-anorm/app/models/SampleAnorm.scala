package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play._
import org.joda.time._

case class SampleAnorm(
  id: Long,
  message: String)

object SampleAnorm {
  val item = {
    get[Long]("id") ~
      get[String]("message") map {
        case id ~ message => SampleAnorm(id, message)
      }
  }

  def findByPk(id: Long): SampleAnorm = {
    DB.withConnection { implicit connection =>
      SQL("select * from sample_anorm where id = {id}").on('id -> id).as(item.single)
    }
  }
}