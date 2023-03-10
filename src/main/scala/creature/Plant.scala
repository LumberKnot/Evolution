package creature

import controller.{Clockable, Drawable, GameObject, Position, Transform}
import simulation.Simulation

import java.awt.Graphics2D

case class Plant(var transform: Transform, reproductionTime : Int, reproductionRange : Double) extends GameObject:

  private var time: Int = 0

  def energy : Double = time

  override def tick(deltaTime : Int, simulation: Simulation) : Unit =
    time += 1

    if time >= reproductionTime then
      time = 0
      reproduce(simulation)


  private def reproduce(simulation: Simulation) : Unit =
    val newPlant = copy(transform = transform.moveTo(transform.position.randomWithin(reproductionRange)))
    simulation.addCommand{simulation => simulation.addGameObject(newPlant)}

  override def draw(g2d: Graphics2D): Unit =
    val (x, y) = transform.position.getTuple
    val radius = 10
    g2d.setColor(java.awt.Color.GREEN)
    g2d.fillOval(x - radius/2, y - radius/2, radius, radius)


