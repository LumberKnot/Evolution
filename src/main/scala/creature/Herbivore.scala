package creature

import controller.{Clockable, Drawable, GameObject, Position, Transform}
import simulation.Simulation

import java.awt.Graphics2D

case class Herbivore(var transform: Transform, attributes: CreatureAttributes) extends GameObject:

  private var energy: Double = attributes.startEnergy

  override def tick(deltaTime: Int, simulation: Simulation): Unit =
    val targetOptional = seek(simulation)
    if targetOptional.isDefined then
      val target = targetOptional.get
      lookAt(target)
      moveForward(deltaTime)
      collide(simulation, target)

    radiantEnergy()
    reproduce(simulation)
    checkEnergy(simulation)

  private def seek(sim: Simulation): Option[Plant] =
    sim.minBy[Plant](plant =>
      transform.position.distanceToSquared(plant.transform.position))

  private def lookAt(plant: Plant): Unit =
    transform = transform.rotateTo(
      transform.angleTo(plant.transform))

  private def collide(sim: Simulation, plant: Plant): Unit =
    if transform.position.distanceToSquared(plant.transform.position) <= attributes.size * attributes.size then
      sim.addCommand(simulation => simulation.removeGameObject(plant))
      energy += plant.energy

  private def moveForward(deltaTime: Int): Unit =
    transform = transform.moved(Position.fromPolar(deltaTime * attributes.speed, transform.rotation))
    energy -= deltaTime * attributes.movementCost

  private def radiantEnergy(): Unit =
    energy -= attributes.size * 0.05

  private def checkEnergy(sim: Simulation): Unit =
    if energy <= 0 then
      sim.addCommand(_.removeGameObject(this))


  def reproduce(sim : Simulation): Unit =
    if energy >= attributes.reproduceEnergy then
      energy -= attributes.reproduceCost
      sim.addCommand(_.addGameObject(Herbivore(Transform(Position(340, 240).randomWithin(300), 0),
        CreatureAttributes(100, 10, 150, 140, 2, 0.3))))

  override def draw(g2d: Graphics2D): Unit =
    val (x, y) = transform.position.getTuple
    g2d.setColor(java.awt.Color.RED)
    g2d.fillOval(x, y, 10, 10)


