package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

import PositionConversions._

class GameSuite extends FunSuite with BeforeAndAfter {

  var game: Game = _

  before {
    game = new Game(columns = 4, rows = 3)
  }

  test("a game of dimensions 4x3 should have 12 tiles") {
    val game = new Game(columns = 4, rows = 3)
    assert(game.tiles.size === 12)
  }

  test("a game of dimensions 3x2 should have 9 tiles") {
    val game = new Game(columns = 3, rows = 3)
    assert(game.tiles.size === 9)
  }

  test("a tile has an original position") {
    assert(game.tiles.head.initialPosition === Position(1, 1))
    assert(game.tiles.last.initialPosition === Position(4, 3))
  }

  test("a tile's current position is initially the initial position") {
    val tile = game.tiles.head
    assert(tile.currentPosition === tile.initialPosition)
  }

  test("changing a tile's current position") {
    val tile = game.tiles.head
    tile.currentPosition = (2, 2)
    assert(tile.currentPosition != tile.initialPosition)
  }

  test("swapping positions between two tiles") {
    val tile1 = game.tiles(0)
    val tile2 = game.tiles(1)
    tile1.swapPositionWith(tile2)
    assert(tile1.currentPosition === tile2.initialPosition)
    assert(tile2.currentPosition === tile1.initialPosition)
  }

  test("a tile knows its game") {
    game.tiles.foreach { tile => assert(tile.game === game) }
  }

  test("a game can return a rect for valid positions") {
     val game4x3 = new Game(columns = 4, rows = 3)
     val game2x2 = new Game(columns = 2, rows = 2)
     assert(game4x3.positionsRect === Rect((1, 1), (4, 3)))
     assert(game2x2.positionsRect === Rect((1, 1), (2, 2)))
  }

  test("a tile knows its adjacent tiles") {
    val game = new Game(columns = 2, rows = 2)
    val topLeft = game.tileAt(1, 1)
    val topRight = game.tileAt(2, 1)
    val bottomLeft = game.tileAt(1, 2)
    val bottomRight = game.tileAt(2, 2)
    assert(topLeft.adjacentTiles === List(topRight, bottomLeft))
    assert(topRight.adjacentTiles === List(topLeft, bottomRight))
  }
  
  
  test("asking if one tile is adjacent to another"){
    val game = new Game(columns = 3, rows = 3)
    assert(game.tileAt(1,1).isAdjacentTo(game.tileAt(2,1)))
    assert(!game.tileAt(1,1).isAdjacentTo(game.tileAt(3,2)))
  }

  test("it is possible to define a hidden tile") {
    val tile = game.tileAt(4, 3)
    game.setHiddenTileAt(4, 3)
    assert(game.hiddenTile === tile)
  }
  
  test("moving to empty slot"){
    val tile1 = game.tileAt(3, 3)
    val tile2 = game.tileAt(4, 3)
    game.setHiddenTileAt(4, 3)
    tile1.moveToEmptySlot
    assert(tile1.currentPosition === Position(4,3))
    assert(tile2.currentPosition === Position(3,3))
  }
  
  test("moving to empty slot when not adjacent to it should do nothing"){
    val tile1 = game.tileAt(1, 1)
    val tile2 = game.tileAt(4, 3)
    game.setHiddenTileAt(4, 3)
    tile1.moveToEmptySlot
    assert(tile1.currentPosition === Position(1,1))
    assert(tile2.currentPosition === Position(4,3))
  }
  
  test("asking if tile can be moved to empty slot"){
    val tile1 = game.tileAt(1, 1)
    val tile2 = game.tileAt(4, 3)
    game.setHiddenTileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }
  
  test("asking if a game has a hidden tile"){
    assert(game.hasHiddenTile === false)
    game.setHiddenTileAt(4, 3)
    assert(game.hasHiddenTile === true)
  }
  
  test("asking if tile can be moved to empty slot, when no tile is hidden"){
    val tile1 = game.tileAt(3, 3)
    val tile2 = game.tileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }
  
  test("making a tile hidden"){
    val tile1 = game.tileAt(1, 1)
    tile1.makeHidden
    assert(game.hiddenTile === tile1)
  }
  
  test("unhiding the tile"){
    assert(game.hasHiddenTile === false)
    val tile = game.tileAt(1, 1)
    tile.makeHidden
    assert(game.hasHiddenTile)
    tile.makeVisible
    assert(game.hasHiddenTile === false)
  }

  test("initially, game is in a solved state") {
    assert(game.isSolved)
  }
  
  test("ask tile if in initial position"){
    assert(game.tileAt(1,1).isAtInitialPosition)
    game.tileAt(2,2).swapPositionWith(game.tileAt(3,2))
    assert(game.tileAt(2,2).isAtInitialPosition === false)
  }

  test("as soon as one move is made, the game is not in solved state") {
    val tile1 = game.tileAt(4,2)
    val tile2 = game.tileAt(4,3)
    tile2.makeHidden
    tile1.moveToEmptySlot
    assert(game.isSolved === false)
  }  
  
  test("asking for a random adjacent tile"){
    val tile = game.tileAt(2,2)
    val possibleTiles = List(
        game.tileAt(3,2),
        game.tileAt(1,2),
        game.tileAt(2,1),
        game.tileAt(2,3))
    (1 to 100).foreach{ _ => assert(possibleTiles.contains(tile.randomAdjacentTile)) }
  }
  
  test("asking for a random move"){
    game.tileAt(4,3).makeHidden
    game.makeRandomMove()
    assert(game.isSolved === false)
  }
  
  test("shuffling tiles"){
    game.tileAt(4,3).makeHidden
    game.makeRandomMove(times=50)
    assume(game.isSolved === false)
  }
  
  test("ask tile to go back to initial position"){
    val tile1 = game.tiles(0)
    val tile2 = game.tiles(1)
    tile1.swapPositionWith(tile2)
    tile1.moveToInitialPosition()
    tile2.moveToInitialPosition()
    
    assert(tile1.isAtInitialPosition)
    assert(tile2.isAtInitialPosition)    
  }

  test("revert to initial state"){
    game.tileAt(4,3).makeHidden
    game.makeRandomMove()
    game.reset()
    assert(game.isSolved)
  }

}
