import play.api.mvc._
import play.Logger

object LoggingFilter2 extends EssentialFilter {
  def apply(next: EssentialAction) = new EssentialAction {
    def apply(rh: RequestHeader) = {
      println(rh.session.data)
      next(rh)
    }
  }
}
