package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._
import org.scalajs.dom.HTMLCanvasElement

trait HtmlBuilder {
  def div(id: String = null, cssClass: String = null): JQuery = {
    val optId = Option(id)
    val optCssClass = Option(cssClass)
    var div = jQuery("<div/>")
    div = optId.map(div.attr("id", _)) getOrElse div
    div = optCssClass.map(div.attr("class", _)) getOrElse div
    div
  }
  def canvas(width: Int, height: Int, cssClass: String = null): HTMLCanvasElement = {
    val classValue = Option(cssClass) getOrElse ""
    val jqCanvas = jQuery(
      s"""<canvas class="$classValue" width="$width" """ +
        s"""height="$height"/>""")
    jqCanvas.get(0).asInstanceOf[HTMLCanvasElement]
  }
  def select(id: String) = (jQuery("<select/>")).attr("id", id)
  def option(value: String, label: String) = (jQuery("<option/>")).attr("value", value).html(label)
  def button(id: String, label: String) = jQuery(s"""<button id="$id">$label</button>""")
  def labelledValue(id: String, label: String, value: String): (JQuery, JQuery) = {
    val outer = jQuery(s"<span>$label </span>")
    val inner = jQuery(s"""<span id="$id">$value</span>""")
    outer.append(inner)
    (outer, inner)
  }
}