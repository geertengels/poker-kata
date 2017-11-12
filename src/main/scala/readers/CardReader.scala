package readers

import model._

/** Read a Card from a 2 character string representation, very defensively.
 * A card representation must consist of exactly 2 characters, of which the first designates
 * a valid Face and the second a valid Suit.
 * 
 * Converts all failures to parse or interpret into IllegalArgumentException
 */
case object CardReader {
  
 def apply(rep: String): Card = {
    try {
      assert(rep!=null, "Card representation cannot be null")
      assert(rep.size==2, "Card representation must contain exactly 2 characters: rep={" + rep + "}")
      Card(Face.fromRep(rep.substring(0,1)),Suit.withName(rep.substring(1,2)))
    } catch {
      case ex: AssertionError => throw new IllegalArgumentException(ex)
      case ex: NoSuchElementException => throw new IllegalArgumentException(ex)
    }
  }
}