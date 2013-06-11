package com.sebnozzi.slidingpuzzle.javafx

import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image

class TilesBoard(img:Image, val columns:Int, val rows:Int) extends Group {

  sliceNodes(img).foreach { this.getChildren().add(_) }

  private def sliceNodes(img: Image): List[Canvas] = {
    def drawBorders(slice: Canvas) {
      val gc = slice.getGraphicsContext2D()
      gc.beginPath()
      gc.rect(0, 0, slice.getWidth(), slice.getHeight())
      gc.closePath()
      gc.stroke()
    }
    val slicer = new ImageSlicer(img, xAmount = columns, yAmount = rows)
    slicer.slicePositions.map {
      case (x, y) =>
        val xCoord = (x - 1) * slicer.sliceWidth
        val yCoord = (y - 1) * slicer.sliceHeight
        val slice = slicer.sliceAt(x, y)
        slice.setLayoutX(xCoord)
        slice.setLayoutY(yCoord)
        drawBorders(slice)
        slice
    }
  }  
  
}