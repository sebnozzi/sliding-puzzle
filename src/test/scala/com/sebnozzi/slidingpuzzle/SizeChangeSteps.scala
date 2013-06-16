package com.sebnozzi.slidingpuzzle

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import cucumber.runtime.PendingException

class SizeChangeSteps extends ScalaDsl with EN with ShouldMatchers {

  Given("""^I am presented with a game in size "([^"]*)"$""") { (gameSizeStr: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  Then("""^the moves counter should be at (\d+)$""") { (expectedMoves: Int) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  When("""^I change the size to "([^"]*)"$""") { (gameSizeStr: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  Then("""^I should see a game with (\d+) tiles$""") { (expectedTileCount: Int) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  Then("""^the game should be in solved state$""") { () =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  
}

