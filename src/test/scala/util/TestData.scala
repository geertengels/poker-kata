package util

import readers.HandReader
import model._
import readers.InputLineReader
import model.categories.Category

object TestData {
  val HAND_BLACK_EMPTY = HandReader("Black: ")
  val HAND_WHITE_EMPTY = HandReader("White: ")
  val HAND_BLACK_AS    = HandReader("Black: AS")
  val HAND_WHITE_AC    = HandReader("White: AC")
  
  val HAND_BLACK_AC    = HandReader("Black: AC")
  val HAND_WHITE_QS    = HandReader("White: QS")
  
  val HAND_AC_2S_3C_KD_QC = HandReader("Hand: AC 2S 3C KD QC")
  val HAND_AS_2C_3S_KC_QS = HandReader("Hand: AS 2C 3S KC QS")
  val HAND_AC_2S_TC_KD_QC = HandReader("Hand: AC 2S 3C KD QC")
  val HAND_AS_2C_4S_KC_QS = HandReader("Hand: AS 2C 4S KC QS")
  
  val HAND_2C_3D_4D_5D_6H = HandReader("Hand: 2C 3D 4D 5D 6H")
  val HAND_2D_3C_4C_5H_6D = HandReader("Hand: 2D 3C 4C 5H 6D")
  
  val REGEX = "Win (.*), (.*)".r
  
  def processTestCases(category: Category, testCases: List[(String,String)]) = {
    for( (input, expectedOutput) <- testCases ) {
      val (hand1, hand2) = InputLineReader(input)
      assertAllGood(input, category.rank(hand1, hand2), expectedOutput)
    }
  }
  
  def assertAllGood(input: String, possResult: Option[Result], expectedOutput: String) {
        
    def myAssert(boolean: Boolean) {
      assert(boolean, input + " => " + expectedOutput + " but {" + possResult + "}")
    }
    
    if (expectedOutput == "Not Ranked") return myAssert( !possResult.isDefined )
    if (expectedOutput == "Draw") return myAssert( possResult.isDefined && !possResult.get.isInstanceOf[Win])
    
    val declaredWin = possResult.get.asInstanceOf[Win]
    
    val REGEX(winningColour, reason) = expectedOutput
    myAssert(winningColour == declaredWin.hand.colour.value)
    myAssert(reason == declaredWin.reason)
  }
  
}