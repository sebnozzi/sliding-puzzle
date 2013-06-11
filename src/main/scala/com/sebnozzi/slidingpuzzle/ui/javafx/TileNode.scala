package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.canvas.Canvas
import com.sebnozzi.slidingpuzzle.model.Position
import javafx.scene.Group
import javafx.scene.Node
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.animation.TranslateTransitionBuilder
import javafx.util.Duration

class TileNode(val parent: TilesBoard, imgSlice: Canvas, pos: Position) extends Group {

  private var onTilePressedCallback: Option[() => Unit] = None

  val initialPosition = pos
  val tileWidth = imgSlice.getWidth()
  val tileHeight = imgSlice.getHeight()

  this.getChildren().add(imgSlice)

  setupTranslation()
  drawBorders(imgSlice)
  setupEventHandler()

  def onMousePressed(callback: () => Unit) {
    onTilePressedCallback = Some(callback)
  }

  def moveToInitialPosition() {
    moveTileTo(initialPosition, animate = false)
  }

  def moveTileTo(pos: Position, animate: Boolean = true) {
    val destX = (pos.col - 1) * tileWidth
    val destY = (pos.row - 1) * tileHeight

    if (animate) {
      animateTo(destX, destY)
    } else {
      translateTo(destX, destY)
    }
  }
  
  def makeHidden() {
    this.setOpacity(0.0)
  }

  def makeVisible() {
    this.setOpacity(1.0)
  }

  def animateTo(x: Double, y: Double) {
    val translateTransition = TranslateTransitionBuilder.create()
      .duration(Duration.seconds(0.3))
      .node(this)
      .toX(x)
      .toY(y)
      .build()
    translateTransition.play()
  }

  def translateTo(x: Double, y: Double) {
    setTranslateX(x)
    setTranslateY(y)
  }

  private def setupEventHandler() {
    imgSlice.setOnMousePressed(new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent) {
        if (onTilePressedCallback.isDefined) {
          (onTilePressedCallback.get)()
        }
      }
    })
  }

  private def setupTranslation() {
    val xCoord = (pos.col - 1) * tileWidth
    val yCoord = (pos.row - 1) * tileHeight
    this.setTranslateX(xCoord)
    this.setTranslateY(yCoord)
  }

  private def drawBorders(slice: Canvas) {
    val gc = slice.getGraphicsContext2D()
    gc.beginPath()
    gc.rect(0, 0, slice.getWidth(), slice.getHeight())
    gc.closePath()
    gc.stroke()
  }

}