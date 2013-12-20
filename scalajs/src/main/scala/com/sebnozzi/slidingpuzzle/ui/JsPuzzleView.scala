package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js
import js.Dynamic

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position

class JsPuzzleView extends PuzzleView {

  val jsController = window.jsUIController

  override def requestFocus() {
    // nothing for now
  }

  val tileViews: List[TileView] = {

    def mkTileView(tileId: String): TileView =
      new TileView() {
        def makeVisible(animate: Boolean) {
          jsController.makeTileVisible(tileId, animate)
        }
        def makeHidden() {
          jsController.makeTileHidden(tileId)
        }
        def moveTileTo(pos: Position, animate: Boolean = false) {
          jsController.moveTileTo(tileId, pos.col, pos.row, animate)
        }
      }

    val tileIds = jsController.getTileIds()
    val tileArray = (tileIds map { tileId => mkTileView(tileId) })
    tileArray.toList
  }

}