package models

import play.api.db.DB
import scala.slick.driver.H2Driver.simple._
import play.api.Play.current

case class Sample(id: Long, name: String)

object SampleDao {
  class SampleTable(tag: Tag) extends Table[Sample](tag, "SAMPLE") {
    def id = column[Long]("ID", O.PrimaryKey)
    def name = column[String]("NAME", O.NotNull)

    def * = (id, name) <> (Sample.tupled, Sample.unapply)
  }

  val tableQuery = TableQuery[SampleTable]
  val database = Database.forDataSource(DB.getDataSource())

  def all(): List[Sample] = database.withSession { implicit session: Session =>
    tableQuery.sortBy(_.id).list
  }
}
