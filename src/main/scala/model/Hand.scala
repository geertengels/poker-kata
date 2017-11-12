package model

import Face._
import Colour._
import Suit._

import scala.util.matching.Regex

/** See corresponding class */
case object Hand {
  
  case class Pair     (face: Face, card1: Card, card2: Card)
  case class Triplet  (face: Face, card1: Card, card2: Card, card3: Card)
  case class Quartet  (face: Face, card1: Card, card2: Card, card3: Card, card4: Card)
  case class Straight (face: Face, card1: Card, card2: Card, card3: Card, card4: Card, card5: Card)
 
  /* Now that we have 2 hands together, we can do some sanity checking.
   * Each Hand is a Set, so cannot contain any duplicates
   * but we can assert that there should be no duplicates across the Hands either.
   * 
   * @TODO: This could be modelled more elegantly by only being able to create
   * Cards by actually TAKING them (and so removing them) one by one from some
   * newly initialised (and to be developed) CardDeck object.
   * If that were done properly, then upon trying to take the same card
   * again, KA-BOOM!!
   */
  def assertNoDuplicates(hand1: Hand, hand2: Hand) = {
    val sharedCards = (hand1.cards & hand2.cards)
    if ( sharedCards.size != 0 ) throw new InvalidCardDeck("two hands sharing cards: " + sharedCards + ": " + hand1 + "," + hand2)
  }
}

/** a Hand has a Colour and a Set of Cards
 *  
 *  a Hand can group its Cards by Face or Suit, count them,
 *  sort by Face, and split itself in possible Pair(s)/Triplet/etc. and a
 *  Hand of remaining Cards
 */
case class Hand(colour: Colour, cards: Set[Card]) {
  import Hand._
    
  // split Hand in an optional Pair, and the remaining Hand
  def pair: (Option[Pair], Hand) = {
    facesOccuringTimes(2) match {
      case firstFace :: _ => (Some(asPair(cardsWithFace(firstFace))), Hand(colour,  cards -- cardsWithFace(firstFace)))
      case Nil => (None, this) // no pair found
    }
  }
   
  // split Hand in two sorted optional Pairs, highest first, and the remaining Hand
  def pairs: (Option[Pair], Option[Pair], Hand) = {
    facesOccuringTimes(2).reverse match {
      case firstFace :: secondFace :: _ => (Some(asPair(cardsWithFace(firstFace))), 
                                            Some(asPair(cardsWithFace(secondFace))), 
                                            Hand(colour, cards -- cardsWithFace(firstFace) -- cardsWithFace(secondFace)))
      case firstFace :: rest => (Some(asPair(cardsWithFace(firstFace))), None, Hand(colour, cards -- cardsWithFace(firstFace)))
      case Nil => (None, None, this) // no pairs found
    }
  }
  
  // split Hand into an optional Triplet, and the remaining Hand
  def triplet: (Option[Triplet], Hand) = {
    facesOccuringTimes(3) match {
      case face :: _ => (Some(asTriplet(cardsWithFace(face))), Hand(colour, cards -- cardsWithFace(face)))
      case Nil => (None, this) // no triplet found
    }
  }
  
  // extract a possible Quartet
  def quartet: (Option[Quartet]) = {
    def asQuartet(cards: Set[Card]): Quartet = {
      Quartet(cards.toList(0).face, cards.toList(0), cards.toList(1), cards.toList(2), cards.toList(3))
    }
    
    facesOccuringTimes(4) match {
      case face :: _ => (Some(asQuartet(cardsWithFace(face))))
      case Nil => None // no quartet found
    }
  }
  
  // return as an optional Straight with its highest Face picked out
  def straight: Option[Straight] = {
  
    def asStraight(cards: List[Card]): Straight = {
      // note we are putting the HIGHEST face in the straight for further comparison
      Straight(cards.toList(4).face, cards.toList(0), cards.toList(1), cards.toList(2), cards.toList(3), cards.toList(4))
    }
    
    val sortedCards = cardsSorted
    if (sortedCards.size != 5) return None
    
    // @TODO: there must be a better way 
    // perhaps trying to get a sorted slice of the Face Enumeration
    // but not seeing it right now
    val isStraight = (sortedCards(0).face.id +1 == sortedCards(1).face.id) &&
                     (sortedCards(1).face.id +1 == sortedCards(2).face.id) &&
                     (sortedCards(2).face.id +1 == sortedCards(3).face.id) &&
                     (sortedCards(3).face.id +1 == sortedCards(4).face.id)
    if (isStraight) return Some(asStraight(sortedCards))
    None
  }
  
  def isFlush: Boolean = {
    suitsOccuringTimes(5).size != 0
  }
  
  // extract the optional triplet in case there is also a pair present
  def fullHouse: Option[Triplet] = {
    val (aTriplet, remainingHand) = triplet
    if (!aTriplet.isDefined) return None
    val (aPair, _) = remainingHand.pair
    if( !aPair.isDefined ) return None
    aTriplet 
  }
  
  def groupByFace: Map[Face, Int] = {
    cards.groupBy( _.face ) map { case (value, cardsOfValue) => (value, cardsOfValue.size) }
  }
  
  def groupBySuit: Map[Suit, Int] = {
    cards.groupBy( _.suit ) map { case (value, cardsOfValue) => (value, cardsOfValue.size) }
  }
      
  def cardsSorted: List[Card] = {
    cards.toList.sorted
  }
  
  def cardsWithFace(face: Face) = {
    cards filter (_.face == face)
  }
  
  override def toString: String = "Hand(" + colour + ":" + " " + cards.mkString(" ") + ")"
  
  private def facesOccuringTimes(thisMany: Int): List[Face] = {
    groupByFace.filter {  case (face, count) => count==thisMany }.keys.toList.sorted
  }
  
  private def suitsOccuringTimes(thisMany: Int): List[Suit] = {
    groupBySuit.filter {  case (suit, count) => count==thisMany }.keys.toList.sorted
  }
  
  private def asPair(cards: Set[Card]): Pair = {
    Pair(cards.toList(0).face, cards.toList(0), cards.toList(1))
  }
  
  private def asTriplet(cards: Set[Card]): Triplet = {
    Triplet(cards.toList(0).face, cards.toList(0), cards.toList(1), cards.toList(2))
  }
}
