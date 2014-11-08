import play.api._
import play.api.mvc._

/**
 * 参考
 * http://www.playframework-ja.org/documentation/2.0.8/ScalaInterceptors
 */
object Global extends WithFilters(LoggingFilter) {
  override def onStart(app: Application) {
    Logger.info("start")
  }

  override def onStop(app: Application) {
    Logger.info("stop")
  }

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    println("executed before every request:" + request.toString)
    println(request.queryString)
    super.onRouteRequest(request)
  }
}