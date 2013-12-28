package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsSize extends js.Object {
  def cols: js.Number = ???
  def rows: js.Number = ???
}

trait JsController extends js.Object {
  def imageLoaded() = ???

  def setupGrid(cols: js.Number, rows: js.Number) = ???
  def findTile(tileId: js.String): js.Object = ???

  def getTileIds(): js.Array[String] = ???
  def getTileWidth(): js.Number = ???
  def getTileHeight(): js.Number = ???
}

trait HasJsController {
  def log(msg: String) = js.Dynamic.global.console.log(msg)
  def jsController: JsController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsController]
}