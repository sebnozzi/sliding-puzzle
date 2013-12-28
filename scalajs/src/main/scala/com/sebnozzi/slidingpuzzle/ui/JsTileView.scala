package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.scalajs.js
import org.scalajs.jquery._

class JsTileView(val tileId: String, tileWidth: Int, tileHeight: Int) extends TileView with HasJsController {

  val tile = jsController.findTile(tileId)
  jQuery(tile).click { () =>
    this.mousePressed
  }

  def makeVisible(animate: Boolean) {
    val tile = jsController.findTile(tileId)
    if (animate) {
      jQuery(tile).show(400)
    } else {
      jQuery(tile).show()
    }
  }

  def makeHidden() {
    val tile = jsController.findTile(tileId)
    jQuery(tile).hide()
  }

  def moveTileTo(pos: Position, animate: Boolean = false) {
    val tile = jsController.findTile(tileId)
    val left = tileWidth * (pos.col - 1)
    val top = tileHeight * (pos.row - 1)
    if (animate) {
      jQuery(tile).animate(
        js.Dictionary(
          ("left", left),
          ("top", top)),
        100)
    } else {
      jQuery(tile).css("top", top)
      jQuery(tile).css("left", left)
    }
  }

}