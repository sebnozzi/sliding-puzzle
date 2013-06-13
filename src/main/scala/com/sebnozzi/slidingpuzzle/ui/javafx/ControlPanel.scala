package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.geometry.Insets
import javafx.scene.control.ToolBar
import javafx.scene.control.Label
import javafx.scene.control.ChoiceBox
import javafx.collections.FXCollections
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

class ControlPanel extends ToolBar {

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")
  private val movesLabel = new Label(movesMsg(0))
  private val sizeSelector = new ChoiceBox[String]()

  setup()

  def onShufflePressed(callback: => Unit) =
    addButtonHandler(shuffleButton) { callback }

  def onResetPressed(callback: => Unit) =
    addButtonHandler(resetButton) { callback }

  def setMovesCount(count: Int) {
    movesLabel.setText(movesMsg(count))
  }

  private def movesMsg(count: Int) = s"Moves: $count"

  private def setup() {
    ControlPanel.this.getItems().add(sizeSelector)
    ControlPanel.this.getItems().add(shuffleButton)
    ControlPanel.this.getItems().add(resetButton)
    ControlPanel.this.getItems().add(movesLabel)

    sizeSelector.setItems(FXCollections.observableArrayList[String]("3x3", "4x3"))
    val selectedIndexProperty = sizeSelector.getSelectionModel().selectedIndexProperty()
    selectedIndexProperty.addListener(new ChangeListener[Number]() {
      def changed(ov: ObservableValue[_ <: Number], 
          oldValue: Number, newValue: Number) {
        println(s"Selected index changed to: $newValue")
      }
    });
  }
  sizeSelector.getSelectionModel().selectFirst()

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}