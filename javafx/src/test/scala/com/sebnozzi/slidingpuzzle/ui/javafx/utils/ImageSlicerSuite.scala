package com.sebnozzi.slidingpuzzle.ui.javafx.utils

import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfter, Ignore}

@Ignore
class ImageSlicerSuite extends AnyFunSuite with BeforeAndAfter {

  val img: Image = {
    val inputStream = this.getClass.getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    assert(inputStream != null, "image not found")
    new Image(inputStream)
  }

  var slicer: ImageSlicer = _

  before {
    slicer = new ImageSlicer(img, GridSize(2, 2))
  }

  test("dimensions") {
    assert(img.getWidth === 840)
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
    assert(slice.getWidth === 420)
    assert(slice.getHeight === 280)
  }

  test("get slice positions") {
    assert(slicer.slicePositions === List((1, 1), (2, 1), (1, 2), (2, 2)))
  }

  test("check dimensions of all slices") {
    val slices: Seq[Canvas] = slicer.allSlices
    slices.foreach { slice =>
      assert(slice.getWidth === 420)
      assert(slice.getHeight === 280)
    }
  }

  test("all slices are different instances") {
    val slicer = new ImageSlicer(img, GridSize(4, 2))
    slicer.allSlices.combinations(2).foreach {
      case Seq(left, right) =>
        assert(left != right)
    }
  }

  test("slice number for position") {
    val slicer = new ImageSlicer(img, GridSize(4, 2))
    assert(slicer.sliceIndexFor(1, 1) === 0)
    assert(slicer.sliceIndexFor(2, 1) === 1)
    assert(slicer.sliceIndexFor(3, 1) === 2)
    assert(slicer.sliceIndexFor(4, 1) === 3)
    assert(slicer.sliceIndexFor(1, 2) === 4)
    assert(slicer.sliceIndexFor(2, 2) === 5)
    assert(slicer.sliceIndexFor(3, 2) === 6)
    assert(slicer.sliceIndexFor(4, 2) === 7)
  }

}
