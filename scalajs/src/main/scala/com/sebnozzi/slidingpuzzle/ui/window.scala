package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsUIController extends js.Object {
  type JsTileType = js.Dynamic
  def getTiles(): Array[JsTileType]
  def makeTileVisible(jsTile: JsTileType, animate: Boolean)
  def makeTileHidden(jsTile: JsTileType)
  def moveTileTo(jsTile: JsTileType, col: Int, row: Int, animate: Boolean)
  def onSizeChanged()
  def setMovesCount(newCount:Int)
}

object window extends js.GlobalScope {
  val jsUIController: JsUIController = ???
}