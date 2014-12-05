package models

import play.api.cache._
import play.api.Play.current

object AuthModel {
  def saveSessionId(sessionId:String, email:String) {
    Cache.set(sessionId, email)
  }

  def isSessionId(sessionId:String):Boolean ={
    return !Cache.get(sessionId).isEmpty
  }

  def getEmail(sessionId:String):Option[String] = {
    println("**" + sessionId + " " + Cache.get(sessionId).get)
    Cache.get(sessionId) match{
      case Some(a) => Some(Cache.get(sessionId).get.asInstanceOf[String])
      case None => None
    }
  }

  def removeSession(sessionId:String){
    Cache.remove(sessionId)
  }
}