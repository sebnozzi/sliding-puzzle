package com.sebnozzi.slidingpuzzle

import js.Dynamic.{ global => g }

object SlidingPuzzleMain {
  def main(): Unit = {
    val paragraph = g.document.createElement("p")
    paragraph.innerHTML = "<strong>It works!</strong>"
    g.document.getElementById("playground").appendChild(paragraph)
  }
}
