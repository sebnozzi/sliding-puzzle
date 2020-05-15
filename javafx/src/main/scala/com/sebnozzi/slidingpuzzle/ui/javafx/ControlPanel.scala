package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.{Button, ChoiceBox, Label, ToolBar}
import javafx.util.StringConverter

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class ControlPanel extends ToolBar {

  // TODO: repeated in ScalaJS
  val gridSizes = List(
    GridSize(3, 2),
    GridSize(3, 3),
    GridSize(4, 3),
    GridSize(6, 4))

  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")
  private val movesLabel = new Label(movesMsg(0))
  private val sizeSelector = new ChoiceBox[GridSize]()

  private var sizeChangeCallback: Option[GridSize => Unit] = None

  setup()

  def onShufflePressed(callback: => Unit): Unit =
    addButtonHandler(shuffleButton) { callback }

  def onResetPressed(callback: => Unit): Unit =
    addButtonHandler(resetButton) { callback }

  def onSizeChange(callback: GridSize => Unit): Unit = {
    sizeChangeCallback = Some(callback)
  }

  def setMovesCount(count: Int): Unit = {
    movesLabel.setText(movesMsg(count))
  }

  def selectGridSize(gridSize: GridSize): Unit = {
    val selectionModel = sizeSelector.getSelectionModel
    selectionModel.select(gridSize)
  }

  private def movesMsg(count: Int): String = s"Moves: $count"

  private def setup(): Unit = {
    ControlPanel.this.getItems.add(sizeSelector)
    ControlPanel.this.getItems.add(shuffleButton)
    ControlPanel.this.getItems.add(resetButton)
    ControlPanel.this.getItems.add(movesLabel)

    sizeSelector.setItems(FXCollections.observableArrayList[GridSize](gridSizes: _*))
    val selectionModel = sizeSelector.getSelectionModel
    selectionModel.selectedIndexProperty().addListener(new ChangeListener[Number]() {
      def changed(ov: ObservableValue[_ <: Number],
        oldValue: Number, newValue: Number): Unit = {
        val newSize: GridSize = sizeSelector.getItems.get(newValue.intValue())
        sizeChanged(newSize)
      }
    })
    sizeSelector.setConverter(new StringConverter[GridSize]() {
      def fromString(str: String): GridSize = ???
      def toString(size: GridSize): String = "%dx%d".format(size.columns, size.rows)
    })
  }

  private def sizeChanged(newSize: GridSize): Unit = {
    if (sizeChangeCallback.isDefined)
      sizeChangeCallback.get.apply(newSize)
  }

  private def addButtonHandler(button: Button)(block: => Unit): Unit = {
    button.setOnAction(new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit = {
        block
      }
    })
  }

}
