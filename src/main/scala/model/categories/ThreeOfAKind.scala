package model.categories

import model._
import Hand._  
import Face._

/* FROM KATA: Three of a Kind: Three of the cards in the hand have the same value. 
 * Hands which both contain three of a kind are ranked by the value of the 3 cards.
 */
object ThreeOfAKind extends Category {
  
  def reason = "Three of a Kind"
  
  	def rank(hand1: Hand, hand2: Hand): PossResult = {
  	  val (triplet1, triplet2) = (hand1.triplet._1, hand2.triplet._1) 	  
  	  neitherOrOnlyOne(triplet1.isDefined, triplet2.isDefined, hand1, hand2) {
  	    compareFaces(triplet1.get.face, triplet2.get.face, hand1, hand2) {
        throw InvalidCardDeck("Two hands with Triplets with same Faces = 6x same Face in Deck!: hands are " + hand1 + " and " + hand2)
  	    }
  	  }
  }
}
