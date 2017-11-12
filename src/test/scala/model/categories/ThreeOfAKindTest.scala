package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import TestData._

@RunWith(classOf[JUnitRunner])
class ThreeOfAKindTest extends FunSuite {
  
  // @TODO: for Three of a Kind we can do an interesting property-based test: 
  // no set of two hands on which three of a kind is applicable should ever rank as a Draw
    
  test("ThreeOfAKind") {
    processTestCases(ThreeOfAKind, List(
      ("Black: 2D 3D 4D 5D 6D  White: 2C 3C 4C 5C 6C", "Not Ranked"), // no triplets
      ("Black: 2D 2C 2H 5D 6D  White: QH 3C 4C 5C 6C", "Win Black, Three of a Kind"), // black has only triplet
      ("Black: 2D 3D 4D 5D 6D  White: AC AH AD 5C 6C", "Win White, Three of a Kind"), // white has only triplet
      ("Black: 2D 2S 2H 5D 6D  White: 3D 3H 3C 5C 6C", "Win White, Three of a Kind: 3 over 2"), // both have triplet, but white the one with higher face
      ("Black: QD QS QH 5D 6D  White: AD AH AC AC 6C", "Win White, Three of a Kind: A over Q") // both have triplet, but black the one with higher face
    ));
  }
}
