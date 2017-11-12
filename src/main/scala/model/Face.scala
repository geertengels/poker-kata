package model

/** a Face represents thirteen ordered values
 *  and has some utilities to go from symbolic constants
 *  to String representations and back
 */
object Face extends Enumeration {
  
    type Face = Value
    
    // map from constant value to a value to be printed out
    // the order is important here, since it will be used for sorting and scoring
    val TWO   = Value("2")
    val THREE = Value("3")
    val FOUR  = Value("4")
    val FIVE  = Value("5")
    val SIX   = Value("6")
    val SEVEN = Value("7")
    val EIGHT = Value("8")
    val NINE  = Value("9")
    val TEN   = Value("T")
    val JACK  = Value("J")
    val QUEEN = Value("Q")
    val KING  = Value("K")
    val ACE   = Value("A")
    
    // read them from String representations
    def fromRep(rep: String): Face = {
      rep match {
        case "A" => ACE
        case "K" => KING
        case "Q" => QUEEN
        case "J" => JACK
        case "T" => TEN
        case _ => withName(rep)
      }
    }
}