package readers

import model._
import Suit._
import Face._
import Colour._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InputLineReaderTest extends FunSuite {
  
  test("reject invalid input lines") {
    
    val invalidReps = List(null,
                           "",
                           "blah",
                           "no colon",
                           "no: second colon",
                           "Only four cards for first player: 2H 3C 4H 5H  White: 2C 3H 4S 8C AH",
                           "Only four cards for second player: 2H 3C 4H 5H 6H  White: 2C 3H 4S 8C",
                           "No double space: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH",
                           "Card with triple character rep: 2HX 3D 5S 9C KD  White: 2C 3H 4S 8C AH",
                           "Card with non-existant face: XH 3D 5S 9C KD  White: 2C 3H 4S 8C AH", 
                           "Card with non-existant suit: AX 3D 5S 9C KD  White: 2C 3H 4S 8C AH")
                                                      
    for(rep <- invalidReps) {
      try {
        val (hand1, hand2) = InputLineReader(rep)
      } catch { 
        case expectedException: IllegalArgumentException => // do nothing, this is expected
        case unexpectedException: Throwable => fail("not expecting anything but IEA:" + unexpectedException)
      }
    } 
  }
  
  test("accept some valid input lines") {
    //these are the ones from the kata page
    
    val validReps = List("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH",
                         "Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S",
                         "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH",
                         "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH")
                         
    for(rep <- validReps) {
      val (hand1, hand2) = InputLineReader(rep)
      assert(hand1 != null)
      assert(hand2 != null)
    }
  }
    
 test("a detailed comparison") {
                          
    val (hand1, hand2) = InputLineReader("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH")
    assert(hand1 != null)
    assert(hand2 != null)
      
    assert(hand1.colour == Colour("Black"))
    val h1Cards = hand1.cards 
    assert(h1Cards.size == 5)
    assert(h1Cards contains Card(TWO,   HEARTS))
    assert(h1Cards contains Card(THREE, DIAMONDS))
    assert(h1Cards contains Card(FIVE,  SPADES))
    assert(h1Cards contains Card(NINE,  CLUBS))
    assert(h1Cards contains Card(KING,  DIAMONDS))
      
    assert(hand2.colour == Colour("White"))
    val h2Cards = hand2.cards 
    assert(h2Cards.size == 5)
    val h2CardsVals = h2Cards.toList
    assert(h2Cards contains Card(TWO,   CLUBS))
    assert(h2Cards contains Card(THREE, HEARTS))
    assert(h2Cards contains Card(FOUR,  SPADES))
    assert(h2Cards contains Card(EIGHT, CLUBS))
    assert(h2Cards contains Card(ACE,   HEARTS))
    
  }
}

