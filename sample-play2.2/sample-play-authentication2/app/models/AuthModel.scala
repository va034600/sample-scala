package models

import play.api.cache._
import play.api.Play.current

object AuthModel {
  def saveSessionId(sessionId:String) {
    //sessionId保存
    Cache.set("sessionId", sessionId)
  }

  def isSessionId(sessionId:String):Boolean ={
    if(Cache.get("sessionId").isEmpty){
      return false
    }

    if(Cache.get("sessionId").isEmpty){
      return false
    }

    return (Cache.get("sessionId").get == sessionId )
  }

  def removeSession(sessionId:String){
    Cache.remove("sessionId")
  }
}