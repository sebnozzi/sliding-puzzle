package com.sebnozzi.slidingpuzzle.ui

import scala.js.annotation.JSName

@JSName("jQuery")
object JQuery extends js.Object {
  def apply(selector: js.String): JQuery = ???
}

trait JQuery extends js.Object {
  def text(value: js.String): JQuery
  def text(): js.String

  def html(value: js.String): JQuery
  def html(): js.String

  def appendTo(parent: JQuery): JQuery
  def append(dom: JQuery)
}