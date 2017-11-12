package model.categories

import model._

/* FROM KATA: High Card: Hands which do not fit any higher category are ranked by the value of their
 * highest card. If the highest cards have the same value, the hands are ranked by
 * the next highest, and so on.
 */
object HighCard extends Category {
  
  def reason = "High Card"
  
  // this is for when HighCard is called as the tie-breaker when another rule applies to both hands and scores them equally
  def rank(originalReason: String, hand1: Hand, hand2: Hand): PossResult = {
    def deepComparison(sortedCardToCard: List[(Card, Card)]): PossResult = {
      sortedCardToCard match {
        case highestCards :: rest => {
          compareFaces(highestCards._1.face, highestCards._2.face, hand1, hand2, originalReason) {
            deepComparison(rest)
          }
        }
        case Nil => Some(Draw)
      }  
    }
    deepComparison(hand1.cardsSorted.reverse zip hand2.cardsSorted.reverse)
  }
  
  // this is the regular invocation of HighCard.rank()
  def rank(hand1: Hand, hand2: Hand): PossResult = {
    rank(reason, hand1, hand2)
  }
}
