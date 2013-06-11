package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import com.sebnozzi.slidingpuzzle.ui.javafx.utils.ImageSlicer
import com.sebnozzi.slidingpuzzle.model.Position
import javafx.animation.TranslateTransitionBuilder
import javafx.util.Duration
import javafx.animation.Timeline

class TilesBoard(img: Image, val columns: Int, val rows: Int) extends Group {

  type TilePressingCallback = Canvas => Unit
  private val slicer = new ImageSlicer(img, xAmount = columns, yAmount = rows)

  private var onTilePressedCallback: Option[TilePressingCallback] = None

  addSliceNodes()

  def onTilePressed(callback: TilePressingCallback) {
    onTilePressedCallback = Some(callback)
  }

  def moveTile(tile: Canvas, destination: Position, animate: Boolean = true) {
    val destinationX = (destination.col - 1) * slicer.sliceWidth
    val destinationY = (destination.row - 1) * slicer.sliceHeight
    
    if (animate) {

      val translateTransition = TranslateTransitionBuilder.create()
        .duration(Duration.seconds(0.3))
        .node(tile)
        .toX(destinationX)
        .toY(destinationY)
        .build()

      translateTransition.play()
    } else {
      tile.setTranslateX(destinationX)
      tile.setTranslateX(destinationY)
    }
  }

  private def addSliceNodes() {
    sliceNodes.foreach { this.getChildren().add(_) }
  }

  private lazy val sliceNodes: List[Canvas] = {
    def drawBorders(slice: Canvas) {
      val gc = slice.getGraphicsContext2D()
      gc.beginPath()
      gc.rect(0, 0, slice.getWidth(), slice.getHeight())
      gc.closePath()
      gc.stroke()
    }
    val slicer = new ImageSlicer(img, xAmount = columns, yAmount = rows)
    slicer.slicePositions.map {
      case (col, row) =>
        val xCoord = (col - 1) * slicer.sliceWidth
        val yCoord = (row - 1) * slicer.sliceHeight
        val slice = slicer.sliceAt(col, row)
        slice.setTranslateX(xCoord)
        slice.setTranslateY(yCoord)
        drawBorders(slice)
        onMousePressedOnSlice(slice) {
          tilePressed(slice)
        }
        slice
    }
  }

  private def tilePressed(tile: Canvas) {
    if (onTilePressedCallback.isDefined)
      onTilePressedCallback.get(tile)
  }

  private def onMousePressedOnSlice(sliceNode: Canvas)(callback: => Unit) {
    sliceNode.setOnMousePressed(new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent) {
        callback
      }
    })
  }

}