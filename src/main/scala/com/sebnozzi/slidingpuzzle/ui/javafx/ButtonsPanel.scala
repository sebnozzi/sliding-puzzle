package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.geometry.Insets
import javafx.scene.control.ToolBar
import javafx.scene.control.Label

class ButtonsPanel extends ToolBar {

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")

  setup()

  def onShufflePressed(callback:  => Unit) =
    addButtonHandler(shuffleButton) { callback }

  def onResetPressed(callback:  => Unit) =
    addButtonHandler(resetButton) { callback }

  private def setup() {
    //this.setSpacing(20.0)
    //this.setPadding(new Insets(20.0))
    //this.setAlignment(Pos.BOTTOM_CENTER)
    //this.setStyle("-fx-background-color: gray;")

    this.getItems().add(shuffleButton)
    this.getItems().add(resetButton)
    this.getItems().add(new Label("Moves: 23"))

    //shuffleButton.setMaxWidth(Double.MaxValue)
    //resetButton.setMaxWidth(Double.MaxValue)
  }

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}