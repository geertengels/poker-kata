package readers

import model._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HandReaderTest extends FunSuite {

  test("reject invalid hand representations") {
     val invalidReps = List( (null,        "null input cannot represent a Hand"),
                             ("",           "empty string cannot represent a Hard"),
                             ("A",          "a Hand must have a colour followed by a colon"),
                             (":",          "colour cannot be empty string"),
                             ("Black: XHX", "a card can only have 2 characters"),
                             ("Black: XH",  "X is not a valid Face"),
                             ("Black: AX",  "X is not a valid Suit")
                           );
    for( (rep, rejectionReason) <- invalidReps) {
      try {
        val hand = HandReader(rep)
        println(hand)
        assert(false, rejectionReason) // if we get here, an exception failed to be thrown
      } catch {
        case expected: IllegalArgumentException => // do nothing
      }
    }
  }
  
  test("accept valid hand representations") {
      val validReps = List( ("Black: ",               0),
                            ("Black: QH",             1),
                            ("Black: 2C QH",          2),
                            ("Black: JD JH JC",       3),
                            ("Black: AS AH AC AD",    4),
                            ("Black: AS AH AC AD KS", 5)
                          );
    for( (rep, numExpectedCardsInHand) <- validReps) {
      try {
        val hand = HandReader(rep)
        println(hand)
        assert(hand != null)
        assert(hand.colour != null && hand.colour.value.size != 0)
        assert(hand.cards.size == numExpectedCardsInHand)
      } catch {
        case ex: IllegalArgumentException => fail(ex.toString)
      }
    }

  }
}
