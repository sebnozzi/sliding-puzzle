package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import javafx.scene.image.Image
import org.scalatest.BeforeAndAfter
import javafx.scene.canvas.Canvas

class SlicerSuite extends FunSuite with BeforeAndAfter {

  val img: Image = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    assert(inputStream != null, "image not found")
    new Image(inputStream)
  }

  var slicer: Slicer = _

  before {
    slicer = new Slicer(img, xAmount = 2, yAmount = 2)
  }

  test("dimensions") {
    assert(img.getWidth() === 840)
    assert(img.getHeight === 560)
  }

  test("slice dimensions") {
    assert(slicer.sliceWidth === 420)
    assert(slicer.sliceHeight === 280)
  }

  test("get coordinates of bottom-right slice") {
    val (x: Int, y: Int) = slicer.coordinatesOfSliceAt(x = 2, y = 2)
    assert(x === 420)
    assert(y === 280)
  }

  test("get coordinates of bottom-left slice") {
    val (x: Int, y: Int) = slicer.coordinatesOfSliceAt(x = 1, y = 2)
    assert(x === 0)
    assert(y === 280)
  }

  test("get one slice") {
    val slice: Canvas = slicer.sliceAt(x = 1, y = 1)
    assert(slice.getWidth() === 420)
    assert(slice.getHeight() === 280)
  }

  test("get slice positions") {
    assert(slicer.slicePositions === List((1, 1), (2, 1), (1, 2), (2, 2)))
  }

  ignore("cut image in 2x2, check dimensions") {
    val slices: Seq[Canvas] = slicer.allSlices
    slices.foreach { slice =>
      assert(slice.getWidth() === 420)
      assert(slice.getHeight() === 280)
    }
  }

}