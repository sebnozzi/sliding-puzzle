package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsUIController extends js.Object {
  def getTileIds(): Array[String]
  def makeTileVisible(tileId: String, animate: Boolean)
  def makeTileHidden(tileId: String)
  def moveTileTo(tileId: String, col: Int, row: Int, animate: Boolean)
  def onSizeChanged()
  def setMovesCount(newCount:Int)
}

object window extends js.GlobalScope {
  val jsUIController: JsUIController = ???
}