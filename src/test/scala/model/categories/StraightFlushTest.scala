package model.categories

import model._
import readers._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import readers.InputLineReader

@RunWith(classOf[JUnitRunner])
class StraightFlushTest extends FunSuite {
  
  // @TODO: for Straight Flush we can do an interesting property-based test: 
  // no set of two hands on straight flush is applicable should ever rank as a Draw
    
  test("StraightFlush") {
    processTestCases(StraightFlush, List(
      ("Black: 2D 3D 4D 5D 8D  White: 2C 3C 4C 5C 7C", "Not Ranked"), // no straight flushes
      ("Black: 2D 3D 4D 5D 6D  White: QH 3C 4C 5C 6C", "Win Black, Straight Flush"), // black has only straight flush
      ("Black: 2D 3D 4D 5D 9D  White: 6C 7C 8C 9C TC", "Win White, Straight Flush"), // white has only straight flush
      ("Black: 2D 3D 4D 5D 6D  White: 3H 4H 5H 6H 7H", "Win White, Straight Flush: 7 over 6") // both have straight flush, but white the one with higher face
    ));
  }
}
