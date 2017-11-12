package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HighCardTest extends FunSuite {
  
  test("HighCard can lead to a draw: empty hands") {
    assertDraw(HighCard.rank(HAND_BLACK_EMPTY, HAND_WHITE_EMPTY))
  }
  
  test("HighCard can lead to a draw: one card each, same value") {
    assertDraw(HighCard.rank(HAND_BLACK_AS, HAND_WHITE_AC))
  }
  
  test("HighCard can lead to a draw: five cards each, same values") {
    assertDraw(HighCard.rank(HAND_AC_2S_3C_KD_QC, HAND_AS_2C_3S_KC_QS))
  }

  private def assertDraw(possibleResult: Option[Result]) = {
    possibleResult match {
      case Some(result) => assert (!result.isInstanceOf[Win] )
      case None => fail("no result")
    }
  }
  
  private def assertWinWithReason(possibleResult: Option[Result], winningHand: Hand, reason: String) = {
    possibleResult match {
      case Some(result) => {
        val win = result.asInstanceOf[Win]
        assert(win.hand === winningHand)
        assert(win.reason === reason)
      }
      case None => fail("no result")
    }
  }
  
  test("HighCard clear win for first hand, one card") {
    assertWinWithReason(HighCard.rank(HAND_BLACK_AC, HAND_WHITE_QS), HAND_BLACK_AC, "High Card: A over Q")
  }
  
  test("HighCard clear win for second hand, one card") {
    val hand1 = Hand(Colour("White"), Set(Card(Face.QUEEN, Suit.SPADES)))
    val hand2 = Hand(Colour("Black"), Set(Card(Face.ACE, Suit.CLUBS)))
    assertWinWithReason(HighCard.rank(HAND_WHITE_QS, HAND_BLACK_AC), HAND_BLACK_AC, "High Card: A over Q")
  }
  
  test("HighCard complicated win: five cards each, fourth one discriminates") {
     assertWinWithReason(HighCard.rank(HAND_AC_2S_3C_KD_QC, HAND_AS_2C_4S_KC_QS), HAND_AS_2C_4S_KC_QS, "High Card: 4 over 3")
  }
  
  test("HighCard complicated draw: five cards each") {
    assertDraw(HighCard.rank(HAND_2C_3D_4D_5D_6H, HAND_2D_3C_4C_5H_6D))
  }
  
  test("now that we have test infrastructure, let us add some") {
    processTestCases(HighCard, List(
      ("Black: 2D 3D 4D 5D 6D  White: 2C 3C 4C 5C 6C", "Draw"), // exact same faces all the way through
      ("Black: 2D 2C 4D 5D 6D  White: 2H 3C 4C 5C 6C", "Win White, High Card: 3 over 2"), // highest 3 faces same, but fourth makes white win
      ("Black: 2D 2C 4D 5D 7D  White: 2H 3C 4C 5C 6C", "Win Black, High Card: 7 over 6") // highest face for white
    ));
  }
  
}