import java.util.ResourceBundle
import org.pac4j.core.client.Clients
import org.pac4j.oauth.client.TwitterClient
import org.pac4j.play.Config
import play.api.Application
import play.api.GlobalSettings
import org.pac4j.oauth.client.Google2Client

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    val resourceBundle = ResourceBundle.getBundle("application")

    val twitterClient = new TwitterClient(
      resourceBundle.getString("twitter_api_key"), 
      resourceBundle.getString("twitter_api_secret"))

    val google2Client = new Google2Client(
      resourceBundle.getString("google_api_key"), 
      resourceBundle.getString("google_api_secret"))
    google2Client.setScope(Google2Client.Google2Scope.EMAIL)

    val clients = new Clients("http://127.0.0.1:9000/callback", twitterClient, google2Client)
    Config.setClients(clients)
  }
}