package dao

import play.api.db.slick.Config.driver.simple._
import play.api.Play.current
import play.api.db.slick._
import entity._

object SampleDao extends Table[Sample]("sample") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)

  def autoInc = id.? ~ name <> (Sample.apply _, Sample.unapply _) returning id
  def * = id.? ~ name <> (Sample.apply _, Sample.unapply _)

  def all(): List[Sample] = DB.withSession { implicit session: Session =>
    Query(SampleDao).sortBy(_.id).list
  }

  def create(sample: Sample):Long = DB.withSession { implicit session: Session =>
    SampleDao.autoInc.insert(sample)
  }
}