package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._

trait HtmlBuilder {
  def div(id: String = null, cssClass: String = null): JQuery = {
    val optId = Option(id)
    val optCssClass = Option(cssClass)
    var div = jQuery("<div/>")
    div = optId.map(div.attr("id", _)) getOrElse div
    div = optCssClass.map(div.attr("class", _)) getOrElse div
    div
  }
  def select(id: String) = (jQuery("<select/>")).attr("id", id)
  def option(value: String, label: String) = (jQuery("<option/>")).attr("value", value).html(label)
  def button(id: String, label: String) = jQuery(s"""<button id="$id">$label</button>""")
  def labelledValue(id: String, label: String, value: String) = jQuery(s"""<span>$label <span id="$id">$value</span></span>""")
}

object UIBuilder extends HtmlBuilder {

  val scalaJsLogo = "resources/img/scala-js-logo-16.png"

  def buildUI(target:JQuery, availableSizes: Seq[(Int, Int)]) {
    jQuery(target).append(gameDiv(availableSizes))
    jQuery(target).append(scalaJsCreditsDiv)
  }

  private def gameDiv(sizes: Seq[(Int, Int)]) = {
    val gameDiv = div(cssClass = "game")
    gameDiv.append(buildToolbar(sizes))
    gameDiv.append(div(id = "puzzle"))
    gameDiv
  }

  private def scalaJsCreditsDiv = {
    val creditsDiv = div(cssClass = "credits")
    creditsDiv.html(s"""Powered by <img src="$scalaJsLogo" 
        valign="middle"/><a href="http://www.scala-js.org/">Scala.js</a>""")
    creditsDiv
  }

  private def sizeSelect(sizes: Seq[(Int, Int)]): JQuery = {
    val selectNode = select(id = "sizeSelector")
    for ((cols, rows) <- sizes) {
      selectNode.append(option(value = s"$cols,$rows",
        label = s"${cols}x${rows}"))
    }
    selectNode
  }

  private def buildToolbar(sizes: Seq[(Int, Int)]): JQuery = {
    val toolbar = div(cssClass = "toolbar")
    toolbar.append(sizeSelect(sizes))
    toolbar.append(button(id = "shuffleButton", label = "Shuffle!"))
    toolbar.append(button(id = "resetButton", label = "Reset"))
    toolbar.append(labelledValue(id = "movesCount", label = "Moves:", value = "0"))
    toolbar
  }

}