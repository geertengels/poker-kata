package model.categories

import model._
import Hand._
import Face._

/* FROM KATA: Full House: 3 cards of the same value, 
 * with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
 */
object FullHouse extends Category {

  def reason = "Full House"
    
  def rank(hand1: Hand, hand2: Hand): PossResult = {
    val (triplet1, triplet2) = (hand1.fullHouse, hand2.fullHouse)
    neitherOrOnlyOne(triplet1.isDefined, triplet2.isDefined, hand1, hand2) {
      compareFaces(triplet1.get.face, triplet2.get.face, hand1, hand2) {
        throw InvalidCardDeck("Two hands with Triplets with same Faces = 6x same Face!: hands are " + hand1 + " and " + hand2)
      }
    }
  }
}
