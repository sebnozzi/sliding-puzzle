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

  val nativeTiles = setupGrid(srcImg, target = nativeView, gridSize.columns, gridSize.rows)

  def setupGrid(srcImg: JsImage, target: js.Object,
    cols: Int, rows: Int): Seq[js.Object] = {
    val tiles = Buffer[js.Object]()

    for (row <- 0 until rows) {
      for (col <- 0 until cols) {
        val id = "canvas_" + col + "_" + row
        val tile = jQuery(
          s"""<canvas id="$id" class="absolute" width="$tileWidth" """ +
            s"""height="$tileHeight"/>""")

        jQuery(target).append(tile)
        val tileDomElement = tile.get(0)

        val ctx = tileDomElement.getContext("2d")
        val sx = col * tileWidth
        val sy = row * tileHeight
        ctx.drawImage(srcImg, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth,
          tileHeight)
        ctx.strokeRect(0, 0, tileWidth, tileHeight)

        jQuery(tile).css("left", sx)
        jQuery(tile).css("top", sy)

        tiles += tile
      }
    }
    tiles.toSeq
  }

  lazy val tileViews: List[JsTileView] = {
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