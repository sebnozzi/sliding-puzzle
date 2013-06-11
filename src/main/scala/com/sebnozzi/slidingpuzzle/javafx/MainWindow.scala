package com.sebnozzi.slidingpuzzle.javafx

import javafx.scene.Scene
import javafx.stage.Stage
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform
import javafx.scene.Group
import javafx.scene.Parent

class MainWindow(mainGroup:Parent) extends Stage {

    val scene = new Scene(mainGroup)
    this.setScene(scene)

    this.setTitle("Sliding Puzzle")
    this.setX(100)
    this.setY(100)
    this.setMinWidth(840)
    this.setMinHeight(560)
    this.setResizable(false)
    this.setFullScreen(false)

    this.setOnCloseRequest(new EventHandler[WindowEvent] {
      def handle(event: WindowEvent) {
        Platform.exit()
      }
    }) 
  
}