package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js
import js.Dynamic
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.collection.mutable.Buffer

class JsPuzzleView extends PuzzleView with HasJsController {

  lazy val tileViews: List[JsTileView] = {
    val ids = jsController.getTileIds()
    val tiles = Buffer[JsTileView]()
    val tileAmount = ids.length.toInt
    for (i <- 0 until tileAmount) {
      val tileId = ids(i)
      tiles += new JsTileView(tileId, jsController)
    }
    tiles.toList
  }

  jsController.onTileClicked((tileId: String) => {
    tileViews.find(_.tileId == tileId).foreach(_.mousePressed)
    js.Object
  })

  override def requestFocus() {
    // nothing for now
  }

}