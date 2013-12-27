package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

import scala.scalajs.js
import org.scalajs.jquery._

class JsAppController extends AppController with HasJsController {

  val imgSrc = "resources/2322324186_ca41fba641_o.jpg"

  override def start() {
    buildBasicUI()
    loadImage(imgSrc, onLoad = { 
      super.start()
    })
  }

  private def loadImage(imgSrc: String, onLoad:  => Unit) {
    val imgThis = jQuery(s"""<img id="srcImg" src="$imgSrc">""")
    imgThis.on("load", () => {
      imgThis.appendTo("#hiddenContainer");
      jsController.imageLoaded();
      onLoad
    });
  }

  override def createAppView(): AppView = {
    new JsAppView()
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView(gridSize)
  }

  private def buildBasicUI() {
    // TODO: move sizes to core?
    val availableSizes = Seq(
      (3, 2),
      (3, 3),
      (4, 3),
      (6, 4))
    val scalaJsCreditsDiv = 
      jQuery("""<div class="credits">Powered by 
          <img src="scala-js-logo-16.png" valign="middle"/><a 
          href="http://www.scala-js.org/">Scala.js</a></div>""")
    val toolbarDiv = {
      val container = jQuery("<div/>").attr("class", "toolbar")
      val selectNode = jQuery("<select/>").attr("id", "sizeSelector")
      for ((cols, rows) <- availableSizes) {
        val sizeOption = jQuery("<option/>").attr("value", s"$cols,$rows").html(s"${cols}x${rows}")
        selectNode.append(sizeOption)
      }
      container.append(selectNode)

      val shuffleButton = jQuery("""<button id="shuffleButton">Shuffle!</button>""")
      val resetButton = jQuery("""<button id="resetButton">Reset</button>""")
      val movesSpan = jQuery("""<span>Moves: <span id="movesCount">0</span></span>""")

      container.append(shuffleButton)
      container.append(resetButton)
      container.append(movesSpan)

      container
    }

    val gameDiv = jQuery("<div/>").attr("class", "game")
    val puzzleDiv = jQuery("<div/>").attr("id", "puzzle")

    gameDiv.append(toolbarDiv)
    gameDiv.append(puzzleDiv)

    val hiddenContainer = {
      jQuery("<div/>")
        .attr("class", "hidden")
        .attr("id", "hiddenContainer")
    }

    jQuery("body").append(gameDiv)
    jQuery("body").append(hiddenContainer)
    jQuery("body").append(scalaJsCreditsDiv)
  }

}