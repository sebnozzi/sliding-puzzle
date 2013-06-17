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
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import javafx.util.StringConverter

class ControlPanel extends ToolBar {

  val gridSizes = List(
    GridSize(3, 2),
    GridSize(3, 3),
    GridSize(4, 3),
    GridSize(6, 4))

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")
  private val movesLabel = new Label(movesMsg(0))
  private val sizeSelector = new ChoiceBox[GridSize]()

  private var sizeChangeCallback: Option[(GridSize) => Unit] = None

  setup()

  def onShufflePressed(callback: => Unit) =
    addButtonHandler(shuffleButton) { callback }

  def onResetPressed(callback: => Unit) =
    addButtonHandler(resetButton) { callback }

  def onSizeChange(callback: (GridSize) => Unit) {
    sizeChangeCallback = Some(callback)
  }

  def setMovesCount(count: Int) {
    movesLabel.setText(movesMsg(count))
  }

  def selectGridSize(gridSize: GridSize) {
    val selectionModel = sizeSelector.getSelectionModel()
    selectionModel.select(gridSize)
  }

  private def movesMsg(count: Int) = s"Moves: $count"

  private def setup() {
    ControlPanel.this.getItems().add(sizeSelector)
    ControlPanel.this.getItems().add(shuffleButton)
    ControlPanel.this.getItems().add(resetButton)
    ControlPanel.this.getItems().add(movesLabel)

    sizeSelector.setItems(FXCollections.observableArrayList[GridSize](gridSizes: _*))
    val selectionModel = sizeSelector.getSelectionModel()
    selectionModel.selectedIndexProperty().addListener(new ChangeListener[Number]() {
      def changed(ov: ObservableValue[_ <: Number],
        oldValue: Number, newValue: Number) {
        val newSize: GridSize = sizeSelector.getItems().get(newValue.intValue())
        sizeChanged(newSize)
      }
    });
    sizeSelector.setConverter(new StringConverter[GridSize]() {
      def fromString(str: String) = ???
      def toString(size: GridSize) = "%dx%d".format(size.columns, size.rows)
    })
  }

  private def sizeChanged(newSize: GridSize) {
    if (sizeChangeCallback.isDefined)
      (sizeChangeCallback.get)(newSize)
  }

  private def addButtonHandler(button: Button)(block: => Unit) {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent) {
        block
      }
    })
  }

}