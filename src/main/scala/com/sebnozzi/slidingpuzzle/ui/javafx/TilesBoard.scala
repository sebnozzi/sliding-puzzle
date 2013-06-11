package com.sebnozzi.slidingpuzzle.ui.javafx

import scala.collection._

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

  private type TilePressingCallback = Canvas => Unit
  private val slicer = new ImageSlicer(img, xAmount = columns, yAmount = rows)
  private var onTilePressedCallback: Option[TilePressingCallback] = None
  private val initialPositionMap = mutable.Map[Canvas, Position]()

  lazy val tiles: List[Canvas] = makeTiles()

  val tileWidth = slicer.sliceWidth
  val tileHeight = slicer.sliceHeight

  addSliceNodes()

  def onTilePressed(callback: TilePressingCallback) {
    onTilePressedCallback = Some(callback)
  }

  def initialPositionOf(tile: Canvas): Position = {
    initialPositionMap.get(tile).get
  }

  def moveToInitialPosition(tile: Canvas) {
    val initialPosition = initialPositionOf(tile)
    moveTile(tile, initialPosition, animate = false)
  }

  def moveTile(tile: Canvas, destination: Position, animate: Boolean = true) {
    val destX = (destination.col - 1) * tileWidth
    val destY = (destination.row - 1) * tileHeight

    if (animate) {
      val translateTransition = TranslateTransitionBuilder.create()
        .duration(Duration.seconds(0.3))
        .node(tile)
        .toX(destX)
        .toY(destY)
        .build()
      translateTransition.play()
    } else {
      tile.setTranslateX(destX)
      tile.setTranslateY(destY)
    }
  }

  private def addSliceNodes() {
    tiles.foreach { this.getChildren().add(_) }
  }

  private def makeTiles() :List[Canvas] = {
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
        val xCoord = (col - 1) * tileWidth
        val yCoord = (row - 1) * tileHeight
        val slice = slicer.sliceAt(col, row)
        slice.setTranslateX(xCoord)
        slice.setTranslateY(yCoord)
        drawBorders(slice)
        initialPositionMap.put(slice, Position(col, row))
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