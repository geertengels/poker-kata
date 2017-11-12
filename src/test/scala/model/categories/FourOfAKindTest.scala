package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FourOfAKindTest extends FunSuite {
  
  import TestData._
  import readers.InputLineReader
  
  // @TODO: for Four of a Kind we can do an interesting property-based test: 
  // no set of two hands on which four of a kind is applicable should ever rank as a Draw
    
  test("FourOfAKind") {
    processTestCases(FourOfAKind, List(
      ("Black: 2D 3D 4D 5D 6D  White: 2C 3C 4C 5C 6C", "Not Ranked"), // no quartets
      ("Black: 2D 2C 2H 2S 6D  White: QH 3C 4C 5C 6C", "Win Black, Four of a Kind"), // black has only quartet
      ("Black: 2D 3D 4D 7D 6D  White: AC AH AD AS 6C", "Win White, Four of a Kind"), // white has only quartet
      ("Black: TD TS TH TC 6D  White: AD AS AH AC 6C", "Win White, Four of a Kind: A over T") // both have quartets, but white the one with higher face
    ));
  }
}
