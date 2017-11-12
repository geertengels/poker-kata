package model.categories

import util.TestData._
import readers.InputLineReader
import model._
import readers._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwoPairsTest extends FunSuite {
      
  test("TwoPairs") {
    processTestCases(TwoPairs, List(
      ("Black: 2D 3D 4D 5D 6D  White: 2C 3C 4C 5C 6C", "Not Ranked"), // no pairs
      ("Black: 2D 2C 4D 5D 6D  White: 2H 3C 4C 5C 6C", "Not Ranked"), // no two pairs
      ("Black: 2D 2C 3D 3C 6D  White: QC 2H 4C 5C 6C", "Win Black, Two Pairs"), // black has two pairs, white none
      ("Black: 2D 2S 4D 5D 6D  White: QC QH KC KS AC", "Win White, Two Pairs"), // black has one pair, white two
      ("Black: 2D 2S 3D 3S 6D  White: QC QH KC KS AC", "Win White, Two Pairs: K over 3"), // both two pairs, white has higher high pair
      ("Black: KD KS 3D 3S 6D  White: QC QH KC KH AC", "Win White, Two Pairs: Q over 3"), // both two pairs, same high pair faces, but white has higher low pair face
      ("Black: KD KS QD QS 6D  White: QC QH KC KH 2C", "Win Black, Two Pairs: 6 over 2") // both two pairs, same high pair faces, same low pair faces, but remaining card makes black win
    ));
  }
}
