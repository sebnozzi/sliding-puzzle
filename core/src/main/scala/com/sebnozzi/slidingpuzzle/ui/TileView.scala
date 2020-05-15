package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position

trait TileView {

  private var onTilePressedCallback: Option[() => Unit] = None

  def mousePressed(): Unit = {
    onTilePressedCallback foreach (callback => callback())
  }

  def onMousePressed(callback: => Unit): Unit = {
    onTilePressedCallback = Some(() => callback)
  }

  def makeVisible(animate: Boolean): Unit

  def makeHidden(): Unit

  def moveTileTo(pos: Position, animate: Boolean = false): Unit

}