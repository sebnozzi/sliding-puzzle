package com.sebnozzi.slidingpuzzle

object worksheet {

  val total = 3                                   //> total  : Int = 3
  
  for(x <- 1 to total) yield x                    //> res0: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 3)
}