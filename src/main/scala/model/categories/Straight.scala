package model.categories

import model._
import Face._
import Hand._

/* FROM KATA: Straight: Hand contains 5 cards with consecutive values.
 * Hands which both contain a straight are ranked by their highest card.
 */
object Straight extends Category {
  
  def reason = "Straight"
  
  def rank(hand1: Hand, hand2: Hand): PossResult = {
    val (straight1, straight2) = (hand1.straight, hand2.straight)
    neitherOrOnlyOne(straight1.isDefined, straight2.isDefined, hand1, hand2) {
      compareFaces(straight1.get.face, straight2.get.face, hand1, hand2) {
        throw InvalidCardDeck("Two hands with Straights with same Faces = 10x same Face in Deck!: hands are " + hand1 + " and " + hand2)  	 
      }
    }
  }
}
