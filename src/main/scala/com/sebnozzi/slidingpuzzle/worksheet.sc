package com.sebnozzi.slidingpuzzle

object worksheet {

class AbstractContainer{
  def setWidget(w:AbstractWidget){}
}

class AbstractWidget{}

class ContainerA{
  def setWidget(w:WidgetA){}
}

class WidgetA extends AbstractWidget


}