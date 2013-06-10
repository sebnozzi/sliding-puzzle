package com.sebnozzi.slidingpuzzle

class Tile(val initialPosition:Position) {
  
  private var _currentPosition = initialPosition
  
  def currentPosition = _currentPosition
  
  def currentPosition_=(newPosition:Position){
    _currentPosition = newPosition
  }
  
}