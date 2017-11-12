package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StraightTest extends FunSuite {
  
  // @TODO: for Three of a Kind we can do an interesting property-based test: 
  // no set of two hands on which three of a kind is applicable should ever rank as a Draw
    
  test("Straight") {
    processTestCases(Straight, List(
      ("Black: 2D 3D 4D 5D 7D  White: 2C 3C 4C 5C 7C", "Not Ranked"), // no straights
      ("Black: 2D 3C 4H 5D 6D  White: QH 3D 4C 5C 6C", "Win Black, Straight"), // black has only straight
      ("Black: 2D 3D 4D 5D 8D  White: 4C 5H 6D 7C 8C", "Win White, Straight"), // white has only straight
      ("Black: 2D 3S 4H 5D 6D  White: 3D 4D 5C 6C 7C", "Win White, Straight: 7 over 6") // both have straight, but white the one with higher face
    ));
  }
}
