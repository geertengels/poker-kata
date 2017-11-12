package model.categories

import model._
import Face._

/** A Category represents a partial scoring rule in Poker.
 *  Concrete Categories are Full House, Three of a Kind, etc.
 */
abstract class Category {
  
  type PossResult = Option[Result]
  
  // To be implemented by each concrete category: provide a printable reason for ranking thus
  def reason: String
  
  // To be implemented by each concrete category: try to rank a pair of Hands
  def rank(hand1: Hand, hand2: Hand): PossResult
  	
  /* Recurring behaviour in Poker ranking:
   * If this category applies to neither hand, return a None result,
   *    declaring this rule not applicable, and effectively falling through
   *    to other categories.
   * If this category applies to only one or the other hand, declare the
   *    winner and give a reason
   * If this category applies to both hands, use the fallback rule provided
   */
  def neitherOrOnlyOne(appliesTo1: Boolean, appliesTo2: Boolean, hand1: Hand, hand2: Hand)(fallback: => PossResult): Option[Result] = {
    if ( !appliesTo1 && !appliesTo2 ) return None // this rule applies to neither hand
    if (  appliesTo1 && !appliesTo2 ) return win(hand1, reason)
    if ( !appliesTo1 &&  appliesTo2 ) return win(hand2, reason)
    fallback
  }
  	
  /* Recurring behaviour in Poker ranking, most often used as
   * a tie-breaker: compare the (highest) Face of some subset of both
   * Hands to declare the winner.
   * If both faces are equal, use the fallback rule provided
   */
  def compareFaces(face1: Face, face2: Face, hand1: Hand, hand2: Hand, mainReason: String = reason)(fallback: => PossResult): PossResult = {
    if ( face1 > face2 ) return win(hand1, mainReason + ": " + face1 + " over " + face2)
    if ( face1 < face2 ) return win(hand2, mainReason + ": " + face2 + " over " + face1)
    fallback
  }
  
  // syntactic sugar
  private def win(hand: Hand, reason: String): PossResult = {
      Some(Win(hand, reason))
  }
}
