package model

import categories._

object Ranker {
   
  // The order in which the categories are listed here is
  // descending in their win potential: higher categories are tried first
  private val orderedCategories = List(
      StraightFlush,
      FourOfAKind,
      FullHouse,
      Flush,
      Straight,
      ThreeOfAKind,
      TwoPairs,
      TwoOfAKind,
      HighCard)
      
  def rank(hand1: Hand, hand2: Hand): Result = {
      def tryCategories(categories: List[Category]): Result = {
       categories match {
         case first :: rest => {
           first.rank(hand1, hand2) match {
             case Some(result) => result
             case None => tryCategories(rest)
           }
         }
         case Nil => throw new IllegalStateException("HighestCard should always be applicable!: " + hand1 + " " + hand2)
        }   
      }
    // we may as well try to detect cheats at this point
    Hand.assertNoDuplicates(hand1, hand2) 
    tryCategories(orderedCategories)
  }
}
