package readers

import model._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/* Mainly a test of the reading of cards from string representation, as implemented in the Card singleton object's apply() method */
@RunWith(classOf[JUnitRunner])
class CardReaderTest extends FunSuite {

  test("reject invalid card representations") {
    val invalidReps = List( (null,  "null input cannot represent a card"),
                            ("",    "empty string cannot represent a card"),
                            ("A",   "single character cannot represent a card - must have at least a face and a suit"),
                            ("XYZ", "more than two characters cannot represent a card - must have at most a face and a suit"),
                            ("XH",  "X does not pick out a valid face - valid faces are 1, 2, ... , 9, T, J, Q, K"),
                            ("AX",  "X does not pick out a valid suit - valid suits are H, C, D, S"));
    for( (rep, rejectionReason) <- invalidReps) {
      try {
        val card = CardReader(rep)
        assert(false, rejectionReason) // if we get here, an exception failed to be thrown
      } catch {
        case expected: IllegalArgumentException => // do nothing
      }
    }
  }
  
  test("accept valid card representations") {
    val validReps = List( ("AS",  "ACE of SPADES"),
                          ("QH",  "QUEEN of HEARTS"),
                          ("2C",  "TWO of CLUBS"),
                          ("JD",  "JACK of DIAMONDS"));
    for( (rep, description) <- validReps) {
      try {
        val card = CardReader(rep)
        assert(card != null) 
      } catch {
        case ex: IllegalArgumentException => fail(ex.toString)
      }
    }
  }
  
  
}