package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.scalajs.js
import org.scalajs.jquery._

class JsTileView(val nativeTile: js.Object, tileWidth: Int, tileHeight: Int) extends TileView {

  jQuery(nativeTile).click { () =>
    this.mousePressed
  }

  def makeVisible(animate: Boolean) {
    if (animate) {
      jQuery(nativeTile).show(400)
    } else {
      jQuery(nativeTile).show()
    }
  }

  def makeHidden() {
    jQuery(nativeTile).hide()
  }

  def moveTileTo(pos: Position, animate: Boolean = false) {
    val left = tileWidth * (pos.col - 1)
    val top = tileHeight * (pos.row - 1)
    if (animate) {
      jQuery(nativeTile).animate(
        js.Dictionary(
          ("left", left),
          ("top", top)),
        100)
    } else {
      jQuery(nativeTile).css("top", top)
      jQuery(nativeTile).css("left", left)
    }
  }

}