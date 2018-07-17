package twitter

import scala.io.Source

/** An object providing several methods used to pre-process tweets from Twitter stream
  * before they are used as input for the gstream algorithm.
  */

object TweetUtils {

  val file = "/home/samaj/ranksnl_large.txt"
  final val stopwords = Source.fromFile(file).getLines.toList

  def splitTweet(s: String): List[String] = if (s.isEmpty) List() else s.trim.split("\\s+").toList

  def removeUrls(lst: List[String]): List[String] = lst.filter(w => !w.startsWith("http"))

  def stripSmallWords(lst: List[String]): List[String] = lst.filter(w => w.length > 2)

  def stripStopWords(s: List[String]): List[String] = ???

}
