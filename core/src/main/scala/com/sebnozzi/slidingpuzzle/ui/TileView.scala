package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position

trait TileView {

  private var onTilePressedCallback: Option[() => Unit] = None

  def mousePressed() {
    onTilePressedCallback foreach (callback => callback())
  }

  def onMousePressed(callback: => Unit) {
    onTilePressedCallback = Some(callback _)
  }

  def makeVisible(animate: Boolean)

  def makeHidden()

  def moveTileTo(pos: Position, animate: Boolean = false)

}