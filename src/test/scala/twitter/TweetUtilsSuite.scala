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

}
