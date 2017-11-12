package readers

import model._

/** Read one hand from a String, very defensively.
 *  A Hand representation must consist of a non-empty string denoting the colour,
 *  followed by a colon, a space, and a possibly empty list of card representations.
 *  
 *  Converts all failures to parse or interpret into IllegalArgumentException
 */
object HandReader {
  
  val REGEX = "(.*): (.*)".r
  
  def apply(rep: String): Hand = {
    try {
      val REGEX(colour, cardsRep) = rep
      val cards = if (cardsRep.size==0) Set() else cardsRep.split(" ").toList map ( CardReader(_) )
      Hand(Colour(colour), Set() ++ cards)
    } catch {
      case ex: MatchError => throw new IllegalArgumentException(ex)
      case ex: NoSuchElementException => throw new IllegalArgumentException(ex)
    }
  }
}