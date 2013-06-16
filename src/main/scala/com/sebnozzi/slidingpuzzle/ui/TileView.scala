package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.Position

trait TileView {

  def mousePressed() {}

  def onMousePressed(callback: => Unit) {}

  def makeVisible(animate: Boolean)

  def makeHidden()

  def moveTileTo(pos: Position, animate: Boolean = false)

}