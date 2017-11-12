package readers

import model._

/** Read one line of input to the Poker kata problem, very defensively.
 *  An InputLine must consist of two valid Hands, separated by a double space.
 *  
 *   * Converts all failures to parse or interpret into IllegalArgumentException
 */
object InputLineReader {
  
  val REGEX = "(.*)  (.*)".r
  
  def apply(rep: String): (Hand, Hand) = {
    try {
      val REGEX(hand1Rep, hand2Rep) = rep
      val (hand1, hand2) = (HandReader(hand1Rep), HandReader(hand2Rep))
      Hand.assertNoDuplicates(hand1, hand2)  // we may as well try to detect cheats at this point
      (hand1, hand2)
    } catch {
      case ex: MatchError => throw new IllegalArgumentException(ex)
    }
  }
}
