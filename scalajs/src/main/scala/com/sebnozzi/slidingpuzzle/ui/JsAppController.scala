package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class JsAppController extends AppController {

  val appContainerId = "appContainer"

  override def createAppView(): AppView = {
    val mainDiv = JQuery("<div id=\"" + appContainerId + "\">")
    mainDiv.appendTo(JQuery("body"))
    new JsAppView(container = mainDiv)
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView()
  }

}