package twitter

import org.scalatest.FunSuite

class TweetUtilsSuite extends FunSuite {

  test("splitTweet returns empty list for empty string") {
    assert(TweetUtils.splitTweet("") == List())
  }

  test("splitTweet returns list of words from regular string") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("This is a tweet") == expected)
  }

  test("splitTweet returns list of words from string with leading whitespace") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("   This is a tweet") == expected)
  }

  test("splitTweet returns list of words from string with trailing whitespace") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("This is a tweet    ") == expected)
  }

  test("splitTweet returns list of words from string with leading and trailing whitespace") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("   This is a tweet    ") == expected)
  }

  test("splitTweet returns list of words from string with variable whitespace between words") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("This is   a     tweet") == expected)
  }

  test("splitTweet returns list of words from string with leading, trailing and variable whitespace") {
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.splitTweet("    This is   a     tweet    ") == expected)
  }

  test("removeUrls returns original list if no url present") {
    val input = List("This", "is", "a", "tweet")
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.removeUrls(input) == expected)
  }

  test("removeUrls returns list with url removed from single-url list") {
    val input = List("This", "is", "a", "tweet", "http://something.great")
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.removeUrls(input) == expected)
  }

  test("removeUrls returns list with url removed from multiple-url list") {
    val input = List("This", "is", "a", "tweet", "http://something.great", "http://somethingelse.great", "http://yetmore.great")
    val expected = List("This", "is", "a", "tweet")
    assert(TweetUtils.removeUrls(input) == expected)
  }

  test("removeUrls returns empty list if only element of list is a url"){
    val input = List("http://something.great")
    val expected = List()
    assert(TweetUtils.removeUrls(input) == expected)
  }

  test("removeUrls returns empty list if empty list is passed in"){
    val input = List()
    val expected = List()
    assert(TweetUtils.removeUrls(input) == expected)
  }

  test("stripSmallWords returns original list if no small words present") {
    val input = List("This", "tweet", "has", "all", "long", "words")
    val expected = List("This", "tweet", "has", "all", "long", "words")
    assert(TweetUtils.stripSmallWords(input) == expected)
  }

  test("stripSmallWords returns list with all small words removed") {
    val input = List("This", "is", "a", "tweet")
    val expected = List("This", "tweet")
    assert(TweetUtils.stripSmallWords(input) == expected)
  }

  test("stripSmallWords returns empty list if all words are small words") {
    val input = List("Th", "is", "a", "tw")
    val expected = List()
    assert(TweetUtils.stripSmallWords(input) == expected)
  }

  test("stripSmallWords returns empty list if empty list passed in") {
    val input = List()
    val expected = List()
    assert(TweetUtils.stripSmallWords(input) == expected)
  }

  test("stripStopWords returns empty list if empty list passed in") {
    val input = List()
    val expected = List()
    assert(TweetUtils.stripStopWords(input) == expected)
  }

  test("stripStopWords returns empty list if all words are stopwords") {
    val input = List("all", "of", "these", "words", "are", "gone")
    val expected = List()
    assert(TweetUtils.stripStopWords(input) == expected)
  }

  test("stripStopWords returns original list if no stopwords present") {
    val input = List("princess", "banana", "hammock")
    val expected = List("princess", "banana", "hammock")
    assert(TweetUtils.stripStopWords(input) == expected)
  }

  test("stripStopWords returns list with all stopwords removed") {
    val input = List("princess", "giving", "banana", "herself", "hammock", "just")
    val expected = List("princess", "banana", "hammock")
    assert(TweetUtils.stripStopWords(input) == expected)
  }

  test("removePunctuation removes unwanted punctuation from a string") {
    val input = "OMG!!! This is a string; it is great."
    val expected = "OMG This is a string it is great"
    assert(TweetUtils.removePunctuation(input) == expected)
  }

  test("removePunctuation does not remove necessary punctuation from a string") {
    val input = "Don't remove the apostrophe in don't!"
    val expected = "Don't remove the apostrophe in don't"
    assert(TweetUtils.removePunctuation(input) == expected)
  }

  test("removePunctuation returns same string if no punctuation present") {
    val input = "No puncutation in here"
    val expected = "No puncutation in here"
    assert(TweetUtils.removePunctuation(input) == expected)
  }

  test("removePunctuation returns empty string if empty string input") {
    val input = ""
    val expected = ""
    assert(TweetUtils.removePunctuation(input) == expected)
  }

  test("removePunctuation returns empty string if only punctuation present") {
    val input = "!!???..."
    val expected = ""
    assert(TweetUtils.removePunctuation(input) == expected)
  }

  test("removePunctuation removes trailing and leading apostrophes") {
    val input = "'hello don't remove' all' 'apostrophes"
    val expected = "hello don't remove all apostrophes"
    assert(TweetUtils.removePunctuation(input) == expected)
  }

}
