package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsImage extends js.Object {
  def width: js.Number = ???
  def height: js.Number = ???
}

trait JsController extends js.Object {
  def setupGrid(srcImg: JsImage, target: js.Object, cols: js.Number, rows: js.Number) = ???
  def getNativeTiles(): js.Array[js.Object] = ???
}

trait JsLogging {
  def log(msg: String) = js.Dynamic.global.console.log(msg)
}

trait HasJsController extends JsLogging {
  def jsController: JsController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsController]
}