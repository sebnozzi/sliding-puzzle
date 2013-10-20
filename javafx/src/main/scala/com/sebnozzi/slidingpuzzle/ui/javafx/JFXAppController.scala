package com.sebnozzi.slidingpuzzle.ui.javafx

import com.sebnozzi.slidingpuzzle.ui.AppController
import javafx.stage.Stage
import javafx.scene.image.Image
import com.sebnozzi.slidingpuzzle.ui.PuzzleView
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.ui.AppView

class JFXAppController(mainWindow: Stage) extends AppController {

  lazy val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def createAppView(): AppView = {
    new JFXAppView(mainWindow)
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JFXPuzzleView(img, gridSize)
  }

}