package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import javafx.scene.image.Image
import org.scalatest.BeforeAndAfter

class SlicerSuite extends FunSuite with BeforeAndAfter {

  val img: Image = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    assert(inputStream != null, "image not found")
    new Image(inputStream)
  }

  test("instantiate a slicer") {
    val slicer = new Slicer(img)
  }

  test("dimensions") {
    assert(img.getWidth() === 840)
    assert(img.getHeight === 560)
  }

  test("cut image in 2x2") {
    val slicer = new Slicer(img)
    val halfHeight = img.getHeight() / 2
    val halfWidth = img.getWidth() / 2
    val slices: Seq[Image] = slicer.cut(2, 2)
    slices.foreach(slice => {
      assert(slice.getHeight() === halfHeight)
      assert(slice.getWidth() === halfWidth)
    })
  }

}