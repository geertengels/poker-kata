package model

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Face._
import Suit._

@RunWith(classOf[JUnitRunner])
class CardTest extends FunSuite {


  test("cards are sorted by their face values, suits are irrelevant") {
    assert( Card(ACE, DIAMONDS) > Card(KING, DIAMONDS) )
    assert( !(Card(TEN, SPADES) > Card(TEN, DIAMONDS)))
    assert( Card(THREE, SPADES) < Card(FOUR, SPADES) )
  }
}
