import play.api.mvc._
import play.Logger

import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object LoggingFilter extends Filter {
  def apply(f: (RequestHeader) => Future[SimpleResult])(requestHeader: RequestHeader): Future[SimpleResult] = {
    val result = f(requestHeader)
    //println("aaa")
    result
  }
}