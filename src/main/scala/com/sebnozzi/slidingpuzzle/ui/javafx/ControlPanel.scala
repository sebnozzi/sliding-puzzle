package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.geometry.Insets
import javafx.scene.control.ToolBar
import javafx.scene.control.Label

class ControlPanel extends ToolBar {

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")
  private val movesLabel = new Label(movesMsg(23))

  setup()

  def onShufflePressed(callback:  => Unit) =
    addButtonHandler(shuffleButton) { callback }

  def onResetPressed(callback:  => Unit) =
    addButtonHandler(resetButton) { callback }

  def setMovesCount(count:Int) {
    movesLabel.setText(movesMsg(count))
  }
  
  private def movesMsg(count:Int) = s"Moves: $count"
  
  private def setup() {
    ControlPanel.this.getItems().add(shuffleButton)
    ControlPanel.this.getItems().add(resetButton)
    ControlPanel.this.getItems().add(movesLabel)
  }

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}