package model

/** A Result can either be a Win for a Hand with a reason, or a Draw */
sealed trait Result
case class Win(hand: Hand, reason: String) extends Result
case object Draw extends Result  
