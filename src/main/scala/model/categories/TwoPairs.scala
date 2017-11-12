package model.categories

import model._
import Hand._
import Face._

/* FROM KATA: Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
 * Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
 */
object TwoPairs extends Category {
  
  def reason = "Two Pairs"
  
  def rank(hand1: Hand, hand2: Hand): PossResult = {
    val (highPair1, lowPair1, remainingHand1) = hand1.pairs
    val (highPair2, lowPair2, remainingHand2) = hand2.pairs
    neitherOrOnlyOne(highPair1.isDefined && lowPair1.isDefined, highPair2.isDefined && lowPair2.isDefined, hand1, hand2) {
      compareFaces(highPair1.get.face, highPair2.get.face, hand1, hand2) {
        compareFaces(lowPair1.get.face, lowPair2.get.face, hand1, hand2) {
          HighCard.rank(reason, remainingHand1, remainingHand2)
        }
      }
    }
  }
}
