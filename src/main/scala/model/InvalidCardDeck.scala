package model

case class InvalidCardDeck(reason: String) extends Throwable(reason)
