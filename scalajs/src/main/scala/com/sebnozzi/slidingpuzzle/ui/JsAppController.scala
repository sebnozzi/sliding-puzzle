package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

import scala.scalajs.js
import org.scalajs.jquery._

class JsAppController extends AppController with JsLogging {

  val imgSrc = "resources/img/2322324186_ca41fba641_o.jpg"
  var srcImg: JsImage = _

  override def start() {
    buildBasicUI()
    loadImage(imgSrc, onLoad = {
      super.start()
    })
  }

  private def loadImage(imgSrc: String, onLoad: => Unit) {
    val imgThis = jQuery(s"""<img id="srcImg">""")
    imgThis.on("load", () => {
      imgThis.appendTo("#hiddenContainer");
      srcImg = jQuery("#srcImg").asInstanceOf[js.Array[JsImage]](0)
      onLoad
    });
    imgThis.attr("src", imgSrc)
  }

  override def createAppView(): AppView = {
    new JsAppView()
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView(srcImg, gridSize)
  }

  // TODO: clean-up UI construction code - split into classes
  
  private def buildBasicUI() {
    // TODO: move sizes to core, make it overridable
    val availableSizes = Seq(
      (3, 2),
      (3, 3),
      (4, 3),
      (6, 4))
    val scalaJsCreditsDiv =
      jQuery("""<div class="credits">Powered by 
          <img src="resources/img/scala-js-logo-16.png" valign="middle"/><a 
          href="http://www.scala-js.org/">Scala.js</a></div>""")
    val toolbarDiv = buildToolbar(availableSizes)

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

  private def buildToolbar(sizes: Seq[(Int, Int)]):JQuery = {
    val container = jQuery("<div/>").attr("class", "toolbar")
    val selectNode = jQuery("<select/>").attr("id", "sizeSelector")
    for ((cols, rows) <- sizes) {
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

}