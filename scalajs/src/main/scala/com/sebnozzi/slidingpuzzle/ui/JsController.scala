package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsController extends js.Object {
  def setupGrid(cols: js.Number, rows: js.Number) = ???
  def getNativeTiles(): js.Array[js.Object] = ???
}

trait HasJsController {
  def log(msg: String) = js.Dynamic.global.console.log(msg)
  def jsController: JsController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsController]
}