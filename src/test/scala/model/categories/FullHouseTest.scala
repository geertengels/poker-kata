package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FullHouseTest extends FunSuite {
  
  import TestData._
  import readers.InputLineReader
  
  // @TODO: for FullHouse we can do an interesting property-based test: 
  // no set of two hands on which FullHouse is applicable should ever rank as a Draw
    
  test("FullHouse") {
    processTestCases(FullHouse, List(
      ("Black: 2D 3D 4D 5D 7C  White: 2C 3C 4C 5H 7D", "Not Ranked"), // no full houses
      ("Black: 2D 2H 3D 3S 3C  White: QH 3H 4C 5C 6C", "Win Black, Full House"), // black has only full house
      ("Black: 2D 3D 4H 5D 8H  White: 4C 4D AS AC AD", "Win White, Full House"), // white has only full house
      ("Black: 2D 2H 3D 3S 3C  White: TC TH TD 8C 8D", "Win White, Full House: T over 3") // both have full house, but white best high face among its triplet
    ));
  }
}
