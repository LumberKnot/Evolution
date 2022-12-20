package creature

import controller.{Clockable, Drawable, GameObject, Position, Transform}
import simulation.Simulation

import java.awt.Graphics2D

case class Herbivore(var transform: Transform, attributes: CreatureAttributes) extends GameObject:

  var energy = attributes.startEnergy
  override def tick(deltaTime: Int, simulation: Simulation): Unit =
    val target = seek(simulation)
    lookAt(target)
    moveForward(deltaTime)
    //checkEnergy()

  private def seek(sim: Simulation) : Option[Plant] =
    sim.minBy[Plant](plant =>
      transform.position.distanceToSquared(plant.transform.position))

  private def lookAt(maybePlant: Option[Plant]) : Unit =
    transform = transform.rotateTo(
      unpackOpt(maybePlant).
      getOrElse(0))

  private def unpackOpt(maybePlant: Option[Plant]) = {
    maybePlant.
      map((plant: Plant) => transform.angleTo(plant.transform))
  }

  private def moveForward(deltaTime: Int): Unit =
    transform = transform.moved(Position.fromPolar(deltaTime * attributes.speed, transform.rotation))

  override def draw(g2d: Graphics2D): Unit =
    val (x, y) = transform.position.getTuple
    g2d.setColor(java.awt.Color.RED)
    g2d.fillOval(x, y, 10, 10)


