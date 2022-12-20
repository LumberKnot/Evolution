package creature

import controller.{Clockable, Drawable, GameObject, Position, Transform}
import simulation.Simulation

import java.awt.Graphics2D

case class Herbivore(var transform: Transform, attributes: CreatureAttributes) extends GameObject:
  override def tick(deltaTime: Int, simulation: Simulation): Unit =
    move(deltaTime)

  private def move(deltaTime: Int): Unit =
    transform = transform.moved(Position.fromPolar(deltaTime * attributes.speed, transform.rotation))

  override def draw(g2d: Graphics2D): Unit =
    val (x, y) = transform.position.getTuple
    g2d.setColor(java.awt.Color.RED)
    g2d.fillOval(x, y, 10, 10)


