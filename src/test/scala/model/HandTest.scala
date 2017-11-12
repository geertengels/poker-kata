package model

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Face._
import Suit._
import readers.HandReader

@RunWith(classOf[JUnitRunner])
class HandTest extends FunSuite {
  
  import TestData._
  
  test("detects duplicates") {
    try {
      Hand.assertNoDuplicates(HAND_AS, HAND_AS)
    } catch {
      case expected: InvalidCardDeck => // do nothing
    }
  }
  
  test("allows non-duplicates") {
    try {
      Hand.assertNoDuplicates(HAND_AS, HAND_AC)
    } catch {
      case ex: Throwable => fail("got exception")
    }
  }
  
  test("groupByFace") {
    assert(HAND_EMPTY.groupByFace.size === 0)
    assert(HAND_AS.groupByFace.size === 1)
    assert(HAND_AS_AC.groupByFace.size === 1)
    assert(HAND_AS_AC_AD.groupByFace.size == 1)
    assert(HAND_AS_AC_2S_2C.groupByFace.size == 2)
  }
  
  test("groupBySuit") {
    assert(HAND_EMPTY.groupBySuit.size === 0)
    assert(HAND_AS.groupBySuit.size === 1)
    assert(HAND_AS_AC.groupBySuit.size === 2)
    assert(HAND_AS_AC_AD.groupBySuit.size == 3)
    assert(HAND_AS_AC_2S_2C.groupByFace.size == 2)
    
  }
  
  test("cardsSorted") {
    assert(HAND_EMPTY.cardsSorted === List() )
    assert(HAND_AS.cardsSorted === List(Card(ACE, SPADES)))
    assert(HAND_2C_3D_4C_QD_AS.cardsSorted === List(Card(TWO, CLUBS), Card(THREE, DIAMONDS), Card(FOUR, CLUBS), Card(QUEEN, DIAMONDS), Card(ACE, SPADES)))
  }
  
  test("cardsWithFace") {
    assert(HAND_EMPTY.cardsWithFace(ACE) === Set() )
    assert(HAND_AS.cardsWithFace(ACE) === Set(Card(ACE, SPADES)))
    assert(HAND_2C_3D_4C_QD_AS.cardsWithFace(ACE) === Set(Card(ACE, SPADES)))
  }
  
  test("pair") {
    assert(!HAND_EMPTY.pair._1.isDefined, "an empty hand as no Pair")
    assert(!HAND_AS.pair._1.isDefined, "a Hand with a single card has no Pair")
    assert(!HAND_AS_KC.pair._1.isDefined, "a Hand with 2 Cards with different Faces has no Pair")
    
    val (pair, remainingHand) = HAND_AS_AC.pair
    assert(pair.isDefined, "pair found: two aces")
    assert(pair.get.card1.face === pair.get.card2.face, "same face")
    assert(pair.get.card1.face === ACE, "ace found 1")
    assert(pair.get.card2.face === ACE, "ace found 2")
    assert(remainingHand.cards.size == 0, "detected Pair removed from remaining Hand")
    
    val (aPair, aRemainingHand) = HAND_AS_AC_QC.pair
    assert(aPair.isDefined, "pair found: two aces")
    assert(aPair.get.card1.face === aPair.get.card2.face, "same face")
    assert(aPair.get.card1.face === ACE, "ace found 1")
    assert(aPair.get.card2.face === ACE, "ace found 2")
    assert(aRemainingHand.cards.size == 1, "detected Pair removed from remaining Hand")
    assert(aRemainingHand.cards.contains(Card(QUEEN, CLUBS)), "correct card remaining")
  }
  
  test("pairs") {
    assert(!HAND_EMPTY.pairs._1.isDefined, "an empty hand as no Pairs")
    assert(!HAND_AS.pairs._1.isDefined, "a Hand with a single card has no Pairs")
    assert(!HAND_AS_KC.pairs._1.isDefined, "a Hand with 2 Cards with different Faces has no Pairs")
    
    val (pair1, pair2, remainingHand) = HAND_AS_AC_QC_QH_3C.pairs
    assert(pair1.isDefined, "pair 1 found")
    assert(pair2.isDefined, "pair 2 found")
    assert(pair1.get.card1.face === ACE, "ace found 1")
    assert(pair1.get.card2.face === ACE, "ace found 2")
    assert(pair2.get.card1.face === QUEEN, "queen found 1")
    assert(pair2.get.card2.face === QUEEN, "queen found 2")
    
    assert(remainingHand.cards.size == 1, "detected Pairs removed from remaining Hand")
    assert(remainingHand.cards.contains(Card(THREE, CLUBS)), "correct card remaining")
  }

  test("triplet") {
    assert(!HAND_EMPTY.triplet._1.isDefined, "an empty hand as no Triplet")
    assert(!HAND_AS.triplet._1.isDefined, "a Hand with a single card has no Triplet")
    assert(!HAND_AS_KC.triplet._1.isDefined, "a Hand with 2 Cards has no Triplet")
    assert(!HAND_AS_AC_QC.triplet._1.isDefined, "a Hand with 3 cards but diff faces has no Triplet")
    
    val (triplet, remainingHand) = HAND_AS_AC_AD.triplet
    assert(triplet.isDefined, "triplet found: three aces")
    assert(triplet.get.card1.face === triplet.get.card2.face, "1 and 2 have same face")
    assert(triplet.get.card2.face === triplet.get.card3.face, "2 and 3 have same face")
    assert(triplet.get.card1.face === ACE, "ace found 1")
    assert(triplet.get.card2.face === ACE, "ace found 2")
    assert(triplet.get.card3.face === ACE, "ace found 3")
    assert(remainingHand.cards.size == 0, "detected Triplet removed from remaining Hand")
    
    val (aTriplet, aRemainingHand) = HAND_AS_AC_AD_QC.triplet
    assert(aTriplet.isDefined, "triplet found: three aces")
    assert(aTriplet.get.card1.face === aTriplet.get.card2.face, "same face")
    assert(aTriplet.get.card1.face === ACE, "ace found 1")
    assert(aTriplet.get.card2.face === ACE, "ace found 2")
    assert(aTriplet.get.card3.face === ACE, "ace found 3")
    assert(aRemainingHand.cards.size == 1, "detected Triplet removed from remaining Hand")
    assert(aRemainingHand.cards.contains(Card(QUEEN, CLUBS)), "correct card remaining")
  }

   test("quartet") {
    assert(!HAND_EMPTY.quartet.isDefined, "an empty hand as no Quartet")
    assert(!HAND_AS.quartet.isDefined, "a Hand with a single card has no Quartet")
    assert(!HAND_AS_KC.quartet.isDefined, "a Hand with 2 Cards has no Quartet")
    assert(!HAND_AS_AC_QC.quartet.isDefined, "a Hand with 3 cards has no Quartet")
    assert(!HAND_AS_AC_AD_QC.quartet.isDefined, "a Hand with 4 cards but not same Faces has no Quartet")
        
    val quartet = HAND_AS_AC_AD_AH.quartet
    assert(quartet.isDefined, "quartet found: four aces")
    assert(quartet.get.card1.face === ACE, "ace found 1")
    assert(quartet.get.card2.face === ACE, "ace found 2")
    assert(quartet.get.card3.face === ACE, "ace found 3")
    assert(quartet.get.card4.face === ACE, "ace found 4")
       
    val aQuartet = HAND_AS_AC_AD_AH_QC.quartet
    assert(aQuartet.isDefined, "quartet found: four aces")
    assert(aQuartet.get.card1.face === ACE, "ace found 1")
    assert(aQuartet.get.card2.face === ACE, "ace found 2")
    assert(aQuartet.get.card3.face === ACE, "ace found 3")
    assert(aQuartet.get.card4.face === ACE, "ace found 4")
  }

  test("straight") {
    assert(!HAND_EMPTY.straight.isDefined, "an empty hand as no Straight")
    assert(!HAND_AS_AC_AD_AH.straight.isDefined, "four cards cannot be Straight")
    assert(!HAND_AS_AC_AD_AH_QC.straight.isDefined, "five cards but no Straight")
    
    assert(!HAND_2C_3D_4C_5D_7D.straight.isDefined, "almost but not quite Straight")
    
    val lowStraight = HAND_2C_3D_4C_5D_6D.straight 
    assert(lowStraight.isDefined, "a low straight")
    assert(lowStraight.get.face === SIX)
    
    val highStraight = HAND_TC_JD_QC_KD_AS.straight 
    assert(highStraight.isDefined, "a high straight")
    assert(highStraight.get.face === ACE)
    
  }
  
  test("flush") {
     assert(!HAND_2C_3D_4C_5D_7D.isFlush, "no flush")
     assert(HAND_2C_4C_6C_8C_TC.isFlush, "flush")
  }
  
  test("fullHouse") {
     val triplet = HAND_2C_3D_4C_5D_7D.fullHouse
     assert( !triplet.isDefined, "not full house")
     
     val aTriplet = HAND_2C_2D_2S_KS_QS.fullHouse
     assert( !aTriplet.isDefined, "not full house but almost")
     
     val anotherTriplet = HAND_2C_2D_KC_KD_KS.fullHouse
     assert( anotherTriplet.isDefined, "full house")
  }
   
}
