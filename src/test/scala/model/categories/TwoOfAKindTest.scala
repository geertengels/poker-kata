package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwoOfAKindTest extends FunSuite {
      
  test("TwoOfAKind") {
    processTestCases(TwoOfAKind, List(
      ("Black: 2D 3D 4D 5D 6D  White: 2C 3C 4C 5C 6C", "Not Ranked"), // no pairs
      ("Black: 2D 2C 4D 5D 6D  White: 2H 3C 4C 5C 6C", "Win Black, Two of a Kind"), // black has only pair
      ("Black: 2D 3D 4D 5D 6D  White: 2C 2H 4C 5C 6C", "Win White, Two of a Kind"), // white has only pair
      ("Black: 2D 2S 4D 5D 6D  White: 2C 2H 4C 5C 6C", "Draw"), // both have a pair, with same face value, and all remaining cards also match by face value
      ("Black: 2D 2C 4D 5D 6D  White: 3C 3H 4C 5C 6C", "Win White, Two of a Kind: 3 over 2"), // both have a pair, but white higher face
      ("Black: 4D 4C 7H 5D 6D  White: 2C 2H 4S 5C 6C", "Win Black, Two of a Kind: 4 over 2"), // both have a pair, but black higher face
      ("Black: 4D 4C 7D 5D 6D  White: 4H 4S 8D 5C 6C", "Win White, Two of a Kind: 8 over 7") // both have a pair, but white has higher face on remaining cards
    ));
  }
}
