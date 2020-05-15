package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.animation.{FadeTransition, TranslateTransition}
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent
import javafx.util.Duration

import com.sebnozzi.slidingpuzzle.model.structs.Position
import com.sebnozzi.slidingpuzzle.ui.TileView

class JFXTileView(imgSlice: Canvas, pos: Position) extends Group with TileView {

  val animationDurationMs = 200

  val initialPosition = pos
  val tileWidth = imgSlice.getWidth()
  val tileHeight = imgSlice.getHeight()

  this.getChildren().add(imgSlice)

  setupTranslation()
  drawBorders(imgSlice)
  setupEventHandler()

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

  def makeVisible(animate: Boolean = false) {
    if (animate) {
      val toVisibleTransition = new FadeTransition(Duration.seconds(0.3), this)
      toVisibleTransition.setDelay(Duration.seconds(0.4))
      toVisibleTransition.setFromValue(0.0)
      toVisibleTransition.setToValue(1.0)
      toVisibleTransition.play()
    } else {
      setOpacity(1.0)
    }
  }

  def animateTo(x: Double, y: Double) {
    val translateTransition = new TranslateTransition(Duration.millis(animationDurationMs), this)
    translateTransition.setToX(x)
    translateTransition.setToY(y)
    translateTransition.play()
  }

  def translateTo(x: Double, y: Double) {
    setTranslateX(x)
    setTranslateY(y)
  }

  private def setupEventHandler() {
    imgSlice.setOnMousePressed(new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent) {
        mousePressed()
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
