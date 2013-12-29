package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js
import org.scalajs.jquery._

trait JsLogging {
  def log(msg: String) = js.Dynamic.global.console.log(msg)
}
