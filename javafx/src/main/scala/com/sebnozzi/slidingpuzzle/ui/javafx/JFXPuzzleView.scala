package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.Group
import javafx.scene.image.Image
import com.sebnozzi.slidingpuzzle.model.structs.{GridSize, Position}
import com.sebnozzi.slidingpuzzle.ui.{PuzzleView, TileView}
import com.sebnozzi.slidingpuzzle.ui.javafx.utils.ImageSlicer

class JFXPuzzleView(img: Image, gridSize: GridSize) extends Group with PuzzleView {

  private val slicer = new ImageSlicer(img, gridSize)

  val tileWidth: Double = slicer.sliceWidth
  val tileHeight: Double = slicer.sliceHeight

  addSliceNodes()

  lazy val tileViews: Seq[JFXTileView] = makeTiles()

  override def requestFocus(): Unit = {
    super[Group].requestFocus()
  }

  private def addSliceNodes(): Unit = {
    tileViews.foreach { this.getChildren.add(_) }
  }

  private def makeTiles(): Seq[JFXTileView] = {
    slicer.slicePositions.map {
      case (col, row) =>
        val imgSlice = slicer.sliceAt(col, row)
        new JFXTileView(imgSlice, Position(col, row))
    }.toSeq
  }

}
