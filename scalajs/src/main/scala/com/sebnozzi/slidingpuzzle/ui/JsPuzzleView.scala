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

    def mkTileView(jsTile: js.Dynamic):TileView =
      new TileView() {
        def makeVisible(animate: Boolean) {
          jsController.makeTileVisible(jsTile, animate)
        }
        def makeHidden() {
          jsController.makeTileHidden(jsTile)
        }
        def moveTileTo(pos: Position, animate: Boolean = false) {
          jsController.moveTileTo(jsTile, pos.col, pos.row, animate)
        }
      }

    val jsTiles = jsController.getTiles()
    val tileArray = (jsTiles map { jsTile => mkTileView(jsTile) })
    tileArray.toList
  }

}