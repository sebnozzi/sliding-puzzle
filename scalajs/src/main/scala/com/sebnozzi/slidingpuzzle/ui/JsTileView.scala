package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.scalajs.js
import org.scalajs.jquery._
import org.scalajs.dom.HTMLCanvasElement

class JsTileView(val tileCanvas: HTMLCanvasElement, tileWidth: Int, tileHeight: Int) extends TileView {

  jQuery(tileCanvas).click { () =>
    this.mousePressed
  }

  def makeVisible(animate: Boolean) {
    if (animate) {
      jQuery(tileCanvas).show(400)
    } else {
      jQuery(tileCanvas).show()
    }
  }

  def makeHidden() {
    jQuery(tileCanvas).hide()
  }

  def moveTileTo(pos: Position, animate: Boolean = false) {
    val left = tileWidth * (pos.col - 1)
    val top = tileHeight * (pos.row - 1)
    if (animate) {
      // note the use of js.Dictionary for a JSON object
      jQuery(tileCanvas).animate(
        js.Dictionary(
          ("left", left),
          ("top", top)),
        100)
    } else {
      jQuery(tileCanvas).css("top", top)
      jQuery(tileCanvas).css("left", left)
    }
  }

}