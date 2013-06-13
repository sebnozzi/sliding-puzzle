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
import com.sebnozzi.slidingpuzzle.model.Size
import javafx.util.StringConverter

class ControlPanel extends ToolBar {

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")
  private val movesLabel = new Label(movesMsg(0))
  private val sizeSelector = new ChoiceBox[Size]()

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

    sizeSelector.setItems(FXCollections.observableArrayList[Size](
      Size(3, 2),
      Size(3, 3),
      Size(4, 3)))
    val selectionModel = sizeSelector.getSelectionModel()
    selectionModel.selectedIndexProperty().addListener(new ChangeListener[Number]() {
      def changed(ov: ObservableValue[_ <: Number],
        oldValue: Number, newValue: Number) {
        val newSize: Size = sizeSelector.getItems().get(newValue.intValue())
        sizeChanged(newSize)
      }
    });
    sizeSelector.setConverter(new StringConverter[Size]() {
      def fromString(str: String) = null
      def toString(size: Size) = "%dx%d".format(size.width, size.height)
    })
    selectionModel.selectFirst()
  }

  private def sizeChanged(newSize: Size) {
    println(s"Selected index changed to: $newSize")
  }

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}