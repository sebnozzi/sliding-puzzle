package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.collection.mutable.Buffer

import scala.scalajs.js
import org.scalajs.jquery._
import js.Dynamic

class JsPuzzleView(srcImg: JsImage, gridSize: GridSize) extends PuzzleView with HasJsController {

  val nativeView = jQuery("#puzzle")
  val tileWidth = Math.floor(srcImg.width / gridSize.columns).toInt
  val tileHeight = Math.floor(srcImg.height / gridSize.rows).toInt
  
  nativeView.empty()
  nativeView.css("width", tileWidth * gridSize.columns)
  nativeView.css("height", tileHeight * gridSize.rows)

  jsController.setupGrid(srcImg, target = nativeView, gridSize.columns, gridSize.rows)

  lazy val tileViews: List[JsTileView] = {
    val nativeTiles = jsController.getNativeTiles()
    val tiles = Buffer[JsTileView]()
    val tileAmount = nativeTiles.length.toInt
    for (i <- 0 until tileAmount) {
      tiles += new JsTileView(nativeTiles(i), tileWidth, tileHeight)
    }
    tiles.toList
  }

  override def requestFocus() {
    // Not applicable to our JavaScript UI
  }

}