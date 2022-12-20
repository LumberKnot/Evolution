package simulation

import controller.*
import creature.*
import window.Window

import scala.collection.mutable.ArrayBuffer

case class Simulation():

  private var _gameTime: Int = 0

  def gameTime: Int = _gameTime

  val gameObjects: ArrayBuffer[GameObject] = ArrayBuffer.empty[GameObject]
  private val commands: ArrayBuffer[SimulationCommand] = ArrayBuffer.empty[SimulationCommand]

  for i <- 0 to 10 do
    addGameObject(Herbivore(Transform(Position(50, 50).randomWithin(100), 0), CreatureAttributes(100, 150, 75, 2, 4)))

  for i <- 0 to 1 do
    addGameObject(Plant(Transform(Position(340, 240).randomWithin(300), 0), 100, 100))

  def addGameObject(gameObject: GameObject): Unit =
    gameObjects += gameObject

  def addCommand(command: SimulationCommand): Unit =
    commands += command

  def minBy[T <: GameObject](f : GameObject => Double) : Option[T] =
    val filtered = gameObjects.collect {
      case v : T => v
    }
    filtered.minByOption(f)

  def tick(deltaTime: Int): Unit =
    gameObjects.foreach(_.tick(deltaTime, this))
    commands.foreach(_(this))
    commands.clear()



    



