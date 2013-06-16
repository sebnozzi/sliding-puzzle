package com.sebnozzi.slidingpuzzle.ui

sealed abstract class ArrowKey

case object Up extends ArrowKey
case object Down extends ArrowKey
case object Left extends ArrowKey
case object Right extends ArrowKey
