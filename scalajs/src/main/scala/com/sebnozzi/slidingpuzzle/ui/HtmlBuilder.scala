package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._
import org.scalajs.dom.html

trait HtmlBuilder {
  def div(cssClass: String = null): JQuery = {
    val optCssClass = Option(cssClass)
    var div = jQuery("<div/>")
    div = optCssClass.map(div.attr("class", _)) getOrElse div
    div
  }
  def canvas(width: Int, height: Int, cssClass: String = null): html.Canvas = {
    val classValue = Option(cssClass) getOrElse ""
    val jqCanvas = jQuery(
      s"""<canvas class="$classValue" width="$width" """ +
        s"""height="$height"/>""")
    jqCanvas.get(0).asInstanceOf[html.Canvas]
  }
  def select() = (jQuery("<select/>"))
  def option(value: String, label: String) = (jQuery("<option/>")).attr("value", value).html(label)
  def button(label: String) = jQuery(s"""<button>$label</button>""")
  def labelledValue(cssClass:String, label: String, value: String): (JQuery, JQuery) = {
    val outer = jQuery(s"""<span class="$cssClass">$label </span>""")
    val inner = jQuery(s"""<span>$value</span>""")
    outer.append(inner)
    (outer, inner)
  }
}
