package model.categories

import model._
import Hand._  
import Face._

/* FROM KATA: Straight flush: 5 cards of the same suit with

 * consecutive values. Ranked by the highest card in the hand.
 */
object StraightFlush extends Category {
  
  def reason = "Straight Flush"
  
  	def rank(hand1: Hand, hand2: Hand): PossResult = {
  	  val straightFlush1 = hand1.straight.isDefined && hand1.isFlush
  	  val straightFlush2 = hand2.straight.isDefined && hand2.isFlush
  	  neitherOrOnlyOne(straightFlush1, straightFlush2, hand1, hand2) {
  	    HighCard.rank(reason, hand1, hand2)
  	  }
  	}
}
