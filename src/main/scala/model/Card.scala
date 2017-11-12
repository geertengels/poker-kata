package model

/** a Card has a Face and a Suit
 *  Cards in Poker are only ever ordered by their Face value, never their Suit
 */
case class Card(face: Face.Value, suit: Suit.Value) extends Ordered[Card] {
  
  override def toString(): String = { face.toString + suit.toString }
  
  def compare(that: Card) = this.face.compareTo(that.face)
}
