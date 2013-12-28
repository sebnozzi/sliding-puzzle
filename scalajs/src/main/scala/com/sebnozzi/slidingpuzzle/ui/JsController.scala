package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsController extends js.Object {
  def setupGrid(cols: js.Number, rows: js.Number) = ???
  def findTile(tileId: js.String): js.Object = ???
  def getTileIds(): js.Array[String] = ???
}

trait HasJsController {
  def log(msg: String) = js.Dynamic.global.console.log(msg)
  def jsController: JsController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsController]
}