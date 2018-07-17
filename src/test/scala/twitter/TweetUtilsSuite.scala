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

}
