package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.Group
import javafx.scene.image.Image

import com.sebnozzi.slidingpuzzle.model.structs.{GridSize, Position}
import com.sebnozzi.slidingpuzzle.ui.PuzzleView
import com.sebnozzi.slidingpuzzle.ui.javafx.utils.ImageSlicer

class JFXPuzzleView(img: Image, gridSize: GridSize) extends Group with PuzzleView {

  private val slicer = new ImageSlicer(img, gridSize)

  val tileWidth = slicer.sliceWidth
  val tileHeight = slicer.sliceHeight

  addSliceNodes()

  lazy val tileViews = makeTiles()

  override def requestFocus() {
    super[Group].requestFocus()
  }

  private def addSliceNodes() {
    tileViews.foreach { this.getChildren().add(_) }
  }

  private def makeTiles() = {
    slicer.slicePositions.map {
      case (col, row) =>
        val imgSlice = slicer.sliceAt(col, row)
        new JFXTileView(imgSlice, Position(col, row))
    }
  }

}
