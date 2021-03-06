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

  def stripStopWords(lst: List[String]): List[String] = lst.filter(w => !stopwords.contains(w))

  def removePunctuation(s: String): String = s.replaceAll("[\\p{Punct}&&[^']]|(?<![a-zA-Z])'|'(?![a-zA-Z])", "")

  def stemWords(lst: List[String]): List[String] = lst.map(w => PorterStemmer(w)).distinct

}