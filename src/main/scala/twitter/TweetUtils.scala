package twitter

/** An object providing several methods used to pre-process tweets from Twitter stream
  * before they are used as input for the gstream algorithm.
  */

object TweetUtils {

  def splitTweet(s: String): List[String] = if (s.isEmpty) List() else s.trim.split("\\s+").toList

  def removeUrls(lst: List[String]): List[String] = lst.filter(w => !w.startsWith("http"))

  def stripSmallWords(s: List[String]): List[String] = ???

  def stripStopWords(s: List[String]): List[String] = ???

}
