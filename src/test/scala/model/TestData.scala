package model

import Suit._
import Face._
import readers.HandReader

object TestData {
  
  private def mkHand1Card(face: Face, suit: Suit): Hand = { Hand(Colour("Some"), Set(Card(face, suit))) }
  
  val HAND_EMPTY = Hand(Colour("Some"), Set())
  val HAND_AS  = mkHand1Card(ACE, SPADES)
  val HAND_AC  = mkHand1Card(ACE, CLUBS)
  
  // going to cheat from here downwards, and let model depend on reader, cause it saves me typing in the tests
  val HAND_AS_AC          = HandReader("Hand: AS AC")
  val HAND_AS_KC          = HandReader("Hand: AS KC")
  val HAND_AS_AC_AD       = HandReader("Hand: AS AC AD")
  val HAND_AS_AC_AD_QC    = HandReader("Hand: AS AC AD QC")
  val HAND_AS_AC_AD_AH    = HandReader("Hand: AS AC AD AH")
  val HAND_AS_AC_2S_2C    = HandReader("Hand: AS AC 2S 2C")
  val HAND_2C_3D_4C_QD_AS = HandReader("Hand: 2C 3D 4C QD AS")
  val HAND_AS_AC_QC       = HandReader("Hand: AS AC QC")
  val HAND_AS_AC_QC_QH_3C = HandReader("Hand: AS AC QC QH 3C")
  val HAND_AS_AC_AD_AH_QC = HandReader("Hand: AS AC AD AH QC")
  val HAND_2C_3D_4C_5D_7D = HandReader("Hand: 2C 3D 4C 5D 7D")
  val HAND_2C_3D_4C_5D_6D = HandReader("Hand: 2C 3D 4C 5D 6D")
  val HAND_TC_JD_QC_KD_AS = HandReader("Hand: TC JD QC KD AS")
  val HAND_2C_4C_6C_8C_TC = HandReader("Hand: 2C 4C 6C 8C TC")
  val HAND_2C_2D_2S_KS_QS = HandReader("Hand: 2C 2D 2S KS QS")
  val HAND_2C_2D_KC_KD_KS = HandReader("Hand: 2C 2D KC KD KS")
}
