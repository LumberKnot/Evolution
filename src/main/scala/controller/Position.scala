package controller

import scala.annotation.targetName

case class Position(x : Int, y : Int):
  def getTuple: (Int, Int) = (x, y)

  def moved(dPosition: Position): Position = this + dPosition
  def magnitude(): Double = math.sqrt(x*x + y*y)
  @targetName("plus")
  infix def +(other: Position): Position = Position(other.x + x, other.y + y)
  @targetName("minus")
  infix def -(other: Position): Position = Position(other.x - x, other.y - y)

object Position:
  def fromIntTuple(tuplePos: (Int, Int)): Position = new Position(tuplePos(0), tuplePos(1))
  def fromPolar(distance: Double, theta: Radians): Position = new Position((distance * math.cos(theta)).toInt, (distance * math.sin(theta)).toInt)


