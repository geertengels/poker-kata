package model

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Face._

@RunWith(classOf[JUnitRunner])
class FaceTest extends FunSuite {
   
  test("faces have values which imply an ordering - try a few symbolic constants") {
    assert( ACE   > TWO   )
    assert( THREE > TWO   )
    assert( TEN   > NINE  )
    assert( JACK  > TEN   )
    assert( QUEEN > JACK  )
    assert( KING  > QUEEN ) 
  }
  
  test("assert on the ordering exhaustively") {
    val pokerOrdering = List("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")
    (Face.values zip pokerOrdering) map { case (found, expected) => assert(found.toString === expected) }
  }
  
}