package com.sebnozzi.slidingpuzzle.ui.javafx

import com.sebnozzi.slidingpuzzle.ui.javafx.utils.ImageSlicer
import javafx.scene.image.Image
import javafx.scene.Group
import com.sebnozzi.slidingpuzzle.model.Position

class TilesBoard(img: Image, val columns: Int, val rows: Int) extends Group {

  private val slicer = new ImageSlicer(img, xAmount = columns, yAmount = rows)

  lazy val tiles: List[TileNode] = makeTiles()

  val tileWidth = slicer.sliceWidth
  val tileHeight = slicer.sliceHeight

  addSliceNodes()

  private def addSliceNodes() {
    tiles.foreach { this.getChildren().add(_) }
  }

  private def makeTiles() = {
    slicer.slicePositions.map {
      case (col, row) =>
        val imgSlice = slicer.sliceAt(col, row)
        new TileNode(this, imgSlice, Position(col, row))
    }
  }

}