package com.sebnozzi.slidingpuzzle.javafx

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.geometry.Insets

class ButtonsPanel extends VBox {

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")

  setup()

  def onShufflePressed(action: () => Unit) =
    addButtonHandler(shuffleButton) { action() }

  def onResetPressed(action: () => Unit) =
    addButtonHandler(resetButton) { action() }

  private def setup() {
    this.setSpacing(20.0)
    this.setPadding(new Insets(20.0))
    this.setAlignment(Pos.BOTTOM_CENTER)
    this.setStyle("-fx-background-color: gray;")

    this.getChildren().add(shuffleButton)
    this.getChildren().add(resetButton)

    shuffleButton.setMaxWidth(Double.MaxValue)
    resetButton.setMaxWidth(Double.MaxValue)
  }

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}