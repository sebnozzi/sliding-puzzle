package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.Parent
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform

object Implicits {

  implicit class MainWindow(window: Stage) {

    def setupWithGroup(mainGroup: Parent) {
      val scene = new Scene(mainGroup)
      window.setScene(scene)

      window.setTitle("Sliding Puzzle")
      window.setX(100)
      window.setY(100)
      window.setMinWidth(840)
      window.setMinHeight(560)
      window.setResizable(false)
      window.setFullScreen(false)

      window.setOnCloseRequest(new EventHandler[WindowEvent] {
        def handle(event: WindowEvent) {
          Platform.exit()
        }
      })
    }
  }

}