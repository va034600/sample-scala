package controllers

import play.api.db._
import dao._
import entity._
import java.sql._
import play.api.Play.current

object SampleModel {
  def all(): List[Sample] = DB.withTransaction { implicit c: Connection =>
    SampleDao.all
  }

  def create(sample: Sample):Long = DB.withTransaction { implicit c: Connection =>
    SampleDao.create(sample)
  }
}