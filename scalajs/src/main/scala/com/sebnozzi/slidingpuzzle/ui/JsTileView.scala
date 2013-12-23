package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position

class JsTileView(val tileId: String, jsController: JsUIController) extends TileView {
  def makeVisible(animate: Boolean) {
    jsController.makeTileVisible(tileId, animate)
  }
  def makeHidden() {
    jsController.makeTileHidden(tileId)
  }
  def moveTileTo(pos: Position, animate: Boolean = false) {
    jsController.moveTileTo(tileId, pos.col - 1, pos.row - 1, animate)
  }
}