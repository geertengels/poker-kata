package model

/** There are 4 possible Suits */
object Suit extends Enumeration {
    type Suit = Value
    
    val SPADES   = Value("S")
    val HEARTS   = Value("H")
    val DIAMONDS = Value("D")
		val CLUBS    = Value("C")
}
