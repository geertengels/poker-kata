package model.categories

import model._
import Hand._
import Face._

/* FROM KATA: Flush: Hand contains 5 cards of the same suit. 
 * Hands which are both flushes are ranked using the rules for High Card. */
object Flush extends Category {

  def reason = "Flush"
  
  	def rank(hand1: Hand, hand2: Hand): PossResult = {
  	  neitherOrOnlyOne(hand1.isFlush, hand2.isFlush, hand1, hand2) {
     	HighCard.rank(reason, hand1, hand2)
  	  }
  	}
}
