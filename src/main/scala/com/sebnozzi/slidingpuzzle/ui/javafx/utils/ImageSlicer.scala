package com.sebnozzi.slidingpuzzle.ui.javafx.utils

import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class ImageSlicer(val img: Image, gridSize: GridSize) {

  val sliceWidth = img.getWidth() / gridSize.columns
  val sliceHeight = img.getHeight() / gridSize.rows

  val slicePositions: List[(Int, Int)] = {
    (for (
      yPos <- 1 to gridSize.rows;
      xPos <- 1 to gridSize.columns
    ) yield (xPos, yPos)).toList
  }

  lazy val allSlices: Seq[Canvas] = {
    slicePositions.map { case (xPos, yPos) => makeSliceAt(xPos, yPos) }
  }

  def sliceAt(x: Int, y: Int): Canvas = {
    val sliceIdx = sliceIndexFor(x, y)
    allSlices(sliceIdx)
  }

  def coordinatesOfSliceAt(x: Int, y: Int): (Int, Int) = {
    val coordX = ((x - 1) * sliceWidth).toInt
    val coordY = ((y - 1) * sliceHeight).toInt
    (coordX, coordY)
  }

  def sliceIndexFor(x: Int, y: Int): Int = {
    (x - 1) + (y - 1) * gridSize.columns
  }

  private def makeSliceAt(x: Int, y: Int): Canvas = {
    val canvas = new Canvas(sliceWidth, sliceHeight)
    val grContext = canvas.getGraphicsContext2D()

    val (sourceX, sourceY) = coordinatesOfSliceAt(x, y)
    val (destX, destY) = (0, 0)

    grContext.drawImage(img,
      sourceX, sourceY, sliceWidth, sliceHeight,
      destX, destY, sliceWidth, sliceHeight)

    canvas
  }

}