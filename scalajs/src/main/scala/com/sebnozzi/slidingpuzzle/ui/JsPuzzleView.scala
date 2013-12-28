package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.collection.mutable.Buffer

import scala.scalajs.js
import org.scalajs.jquery._
import js.Dynamic


class JsPuzzleView(gridSize: GridSize) extends PuzzleView with HasJsController {

  jsController.setupGrid(gridSize.columns, gridSize.rows)
  
  jQuery("#puzzle").css("width", jsController.getTileWidth * gridSize.columns)
  jQuery("#puzzle").css("height", jsController.getTileHeight * gridSize.rows)  
  
  lazy val tileViews: List[JsTileView] = {
    val ids = jsController.getTileIds()
    val tiles = Buffer[JsTileView]()
    val tileAmount = ids.length.toInt
    for (i <- 0 until tileAmount) {
      val tileId = ids(i)
      tiles += new JsTileView(tileId)
    }
    tiles.toList
  }

  override def requestFocus() {
    // Not applicable to our JavaScript UI
  }

}