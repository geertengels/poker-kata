package model.categories

import model._
import Face._
import Hand._

/* FROM KATA: Pair (here renamed as TwoOfAKind): 2 of the 5 cards in the hand have the same value. Hands which both
 * contain a pair are ranked by the value of the cards forming the pair. If these
 * values are the same, the hands are ranked by the values of the cards not
 * forming the pair, in decreasing order.
 */
object TwoOfAKind extends Category {
  
  def reason = "Two of a Kind"
  	 
  def rank(hand1: Hand, hand2: Hand): PossResult = {
    val (pair1, remainingHand1) = hand1.pair
    val (pair2, remainingHand2) = hand2.pair
    neitherOrOnlyOne(pair1.isDefined, pair2.isDefined, hand1, hand2) {
      compareFaces(pair1.get.face, pair2.get.face, hand1, hand2) {
        (HighCard.rank(reason, remainingHand1, remainingHand2))
      }
    }
  }
}
