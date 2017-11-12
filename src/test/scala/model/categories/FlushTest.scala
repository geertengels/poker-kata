package model.categories

import model._
import util.TestData._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlushTest extends FunSuite {
  
  // @TODO: for Flush we can do an interesting property-based test: 
  // no set of two hands on which flush is applicable should ever rank as a Draw
  
  test("Flush") {
    processTestCases(Flush, List(
      ("Black: 2D 3D 4D 5D 7C  White: 2C 3C 4C 5H 7D", "Not Ranked"), // no flushes
      ("Black: 2D 3D 4D 5D 6D  White: QH 3H 4C 5C 6C", "Win Black, Flush"), // black has only flush
      ("Black: 2D 3D 4D 5D 8H  White: 4C 5C 6C 8C TC", "Win White, Flush"), // white has only flush
      ("Black: 2D 3D 4D 5D 8D  White: 4C 5C 6C 8C TC", "Win White, Flush: T over 8") // both have flush, but white best high face
    ));
  }
}
