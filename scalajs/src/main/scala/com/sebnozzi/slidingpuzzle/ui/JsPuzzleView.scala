package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position
import scala.collection.mutable.Buffer
import scala.scalajs.js
import org.scalajs.jquery._
import js.Dynamic
import org.scalajs.dom.HTMLImageElement
import org.scalajs.dom.HTMLCanvasElement

class JsPuzzleView(srcImg: HTMLImageElement, gridSize: GridSize) extends PuzzleView {

  val cols = gridSize.columns
  val rows = gridSize.rows
  
  val nativeView = jQuery("#puzzle")
  val tileWidth = Math.floor(srcImg.width / cols).toInt
  val tileHeight = Math.floor(srcImg.height / rows).toInt

  nativeView.empty()
  nativeView.css("width", tileWidth * cols)
  nativeView.css("height", tileHeight * rows)

  val tileViews: List[JsTileView] = {
    val tiles = Buffer[JsTileView]()

    for (row <- 0 until rows) {
      for (col <- 0 until cols) {
        val tile = jQuery(
          s"""<canvas class="absolute" width="$tileWidth" """ +
            s"""height="$tileHeight"/>""")

        jQuery(nativeView).append(tile)
        
        val tileCanvas = tile.get(0).asInstanceOf[HTMLCanvasElement] 
        val ctx = {
          tileCanvas.getContext("2d")
        }
        
        // Copy image part into tile
        val sx = col * tileWidth
        val sy = row * tileHeight
        ctx.drawImage(srcImg, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth,
          tileHeight)
          
        // Draw tile border
        ctx.strokeRect(0, 0, tileWidth, tileHeight)

        // Position tile
        jQuery(tile).css("left", sx)
        jQuery(tile).css("top", sy)

        tiles += new JsTileView(tileCanvas, tileWidth, tileHeight)
      }
    }
    tiles.toList
  }

  override def requestFocus() {
    // Not applicable to our JavaScript UI
  }

}