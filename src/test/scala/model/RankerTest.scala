package model

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Suit._
import Face._
import readers._
import categories.Category
import util.TestData._

@RunWith(classOf[JUnitRunner])
class RankerTest extends FunSuite {
  
  test("Ranker") {
    processTestCases(RankerWrapper, List(
    
      // Suggested test cases from the KATA, but not there they do indeed rank 3rd case wrong!
      ("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH", "Win White, High Card: A over K"),
      ("Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S", "Win Black, Full House"),
      ("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH", "Win Black, High Card: 9 over 8"),
      ("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH", "Draw"),
    
      // Since the individual Rankers have exhaustive unit tests each,
      // this can focus on the question whether they are applied in the right order
      // with the right precedence
      
      // Straight Flush over Four of a Kind
      ("Black: 2H 3H 4H 5H 6H  White: AC AH AS AS KH", "Win Black, Straight Flush"),
      
      // Four of a Kind over Full House
      ("Black: 2H 2C 4H 4C 4D  White: AC AH AS AD KH", "Win White, Four of a Kind"),

      // Full House over Flush
      ("Black: 2H 2C 4H 4C 4D  White: 8C TC 5C 6C AC", "Win Black, Full House"),

      // Flush over Straight
      ("Black: 2H 3C 4H 5D 6D  White: 8C TC 5C 6C AC", "Win White, Flush"),

      // Straight over Three of a Kind
      ("Black: 2H 3C 4H 5D 6D  White: 8C 8D 8S 6H AC", "Win Black, Straight"),

      // Three of a Kind over Two Pairs
      ("Black: 2H 2C 4H 4D 6D  White: 8C 8D 8S 6H AC", "Win White, Three of a Kind"),

      // Two Pairs over Two of a Kind
      ("Black: 2H 2C 4H 4D 6D  White: 8C 8D 7S 6H AC", "Win Black, Two Pairs"),
      
      // Two of a Kind over High Card
      ("Black: AH 2C 4H 5D 6D  White: 8C 8D 7S 6H AC", "Win White, Two of a Kind")

    ));
  }
  
  // utility wrapper just so we can use a Ranker as a Category now that we have built
  // the infrastructure to make testing Categories easy.
  // smooths over the differences: Ranker always has Result, not PossResult,
  // and Ranker does not provide a reason where Category does
  private object RankerWrapper extends Category {
    
    val reason = "Whole Game"
    
    def rank(hand1: Hand, hand2: Hand): PossResult = {
      Some(Ranker.rank(hand1,hand2))
    }
  }
}
