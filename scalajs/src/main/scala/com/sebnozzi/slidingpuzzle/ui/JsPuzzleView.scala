package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import org.scalajs.dom.html
import org.scalajs.jquery._


class JsPuzzleView(srcImg: html.Image, nativeView:JQuery, gridSize: GridSize)
    extends PuzzleView with HtmlBuilder {

  val cols = gridSize.columns
  val rows = gridSize.rows

  val tileWidth = Math.floor(srcImg.width / cols).toInt
  val tileHeight = Math.floor(srcImg.height / rows).toInt

  nativeView.empty()
  nativeView.css("width", tileWidth * cols)
  nativeView.css("height", tileHeight * rows)

  val tileViews: List[JsTileView] = (for {
    row <- 0 until rows
    col <- 0 until cols
  } yield {
    val tileCanvas = canvas(
      width = tileWidth,
      height = tileHeight,
      cssClass = "absolute")

    jQuery(nativeView).append(tileCanvas)

    val sx = col * tileWidth
    val sy = row * tileHeight

    // Position tile
    jQuery(tileCanvas).css("left", sx)
    jQuery(tileCanvas).css("top", sy)

    val ctx = tileCanvas.getContext("2d")

    // Copy image part into tile
    ctx.drawImage(srcImg, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth,
      tileHeight)

    // Draw tile border
    ctx.strokeRect(0, 0, tileWidth, tileHeight)

    new JsTileView(tileCanvas, tileWidth, tileHeight)
  }).toList

  override def requestFocus() {
    // Not applicable to our JavaScript UI
  }

}
