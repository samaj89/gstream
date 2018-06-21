package twitter

import java.io.FileInputStream
import java.util.Properties

import com.github.nscala_time.time.Imports.DateTimeFormat
import net.liftweb.json.JsonAST.JNothing
import net.liftweb.json.{JString, parse}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.twitter.TwitterSource

object TwitterStreamReader {

  def main(args: Array[String]) {

    case class Tweet(
                            id:String,
                            creationTime:Long,
                            user:String,
                            text:String
                          )

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val prop = new Properties()
    val propFilePath = "/home/samaj/twitter.properties"

    try {

      prop.load(new FileInputStream(propFilePath))
      prop.getProperty("twitter-source.consumerKey")
      prop.getProperty("twitter-source.consumerSecret")
      prop.getProperty("twitter-source.token")
      prop.getProperty("twitter-source.tokenSecret")

    } catch {
      case e: Exception =>
        e.printStackTrace()
        sys.exit(1)
    }

    val streamSource = env.addSource(new TwitterSource(prop))

    val filteredStream = streamSource.filter( value =>  value.contains("created_at"))

    val parsedStream = filteredStream.map(
      record => {
        parse(record)
      }
    )

    val structuredStream:DataStream[Tweet] = parsedStream.filter(
      record => record \ "retweeted_status" == JNothing && (record \ "lang" \\ classOf[JString] ).head.toString == "en").map(
      record => {
        Tweet(
          ( record \ "id_str" \\ classOf[JString] ).head.toString
          , DateTimeFormat
            .forPattern("EEE MMM dd HH:mm:ss Z yyyy")
            .parseDateTime(
              ( record \ "created_at" \\ classOf[JString] ).head
            ).getMillis
          , ( record \ "user" \ "name" \\ classOf[JString] ).head.toString
          , ( record \ "text" \\ classOf[JString] ).head.toString
        )

      }
    )

    structuredStream.print

    env.execute("Twitter Stream Reader")
  }

}