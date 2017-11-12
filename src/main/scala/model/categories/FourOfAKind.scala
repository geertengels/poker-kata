package model.categories

import model._
import Hand._
import Face._

/* FROM KATA: Four of a kind: 4 cards with the same value. Ranked by the value of the 4 cards.
 */
object FourOfAKind extends Category {
  
  def reason = "Four of a Kind"
  
  	def rank(hand1: Hand, hand2: Hand): PossResult = {
  	  neitherOrOnlyOne(hand1.quartet.isDefined, hand2.quartet.isDefined, hand1, hand2) {
  	    compareFaces(hand1.quartet.get.face, hand2.quartet.get.face, hand1, hand2) {
  	      throw InvalidCardDeck("Two hands with Quartets with same Faces = 8x same Face in CardDeck!: hands are " + hand1 + " and " + hand2)
  	    }
  	  }
  	}
}
