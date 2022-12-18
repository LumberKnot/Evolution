package controler

import scala.util.Random

case class Position(x : Double, y : Double):

  lazy val random : Random = Random()
  def randomWithin(range: Double) : Position =
    val angle = random.nextDouble() * 2 * Math.PI
    val r = random.nextDouble() * range

    Position(r * Math.cos(angle), r * Math.sin(angle))


