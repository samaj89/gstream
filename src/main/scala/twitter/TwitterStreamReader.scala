package twitter

import java.io.FileInputStream
import java.util.Properties
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.twitter.TwitterSource

object TwitterStreamReader {

  def main(args: Array[String]) {

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

    streamSource.print

    env.execute("Twitter Stream Reader")
  }

}
