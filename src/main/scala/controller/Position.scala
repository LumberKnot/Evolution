package controller

import controller.Position.fromPolar

import scala.annotation.targetName
import scala.util.Random

case class Position(x : Int, y : Int):
  def getTuple: (Int, Int) = (x, y)
  def magnitude(): Double = math.sqrt(x*x + y*y)
  @targetName("plus")
  infix def +(other: Position): Position = Position(other.x + x, other.y + y)
  @targetName("minus")
  infix def -(other: Position): Position = Position(other.x - x, other.y - y)

  lazy val random : Random = Random()
  def randomWithin(range : Double) : Position =
    val angle = random.nextDouble() * 2 * Math.PI
    val r = random.nextDouble() * range
    fromPolar(r,angle)


object Position:
  def fromIntTuple(tuplePos: (Int, Int)): Position = new Position(tuplePos(0), tuplePos(1))
  def fromPolar(distance: Double, theta: Radians): Position = new Position((distance * math.cos(theta)).toInt, (distance * math.sin(theta)).toInt)


