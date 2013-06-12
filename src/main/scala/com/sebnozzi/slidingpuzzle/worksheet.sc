package com.sebnozzi.slidingpuzzle

object worksheet {



  def onEvent(callback: => Unit){
    val _callback = callback _
    println("First this")
    _callback
  }                                               //> onEvent: (callback: => Unit)Unit

  onEvent{
    println("Hello")
  }                                               //> First this


}