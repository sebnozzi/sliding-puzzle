package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._

trait JqToolbar {
  def shuffleButton: JQuery
  def resetButton: JQuery
  def sizeSelect: JQuery
  def movesCount: JQuery
}