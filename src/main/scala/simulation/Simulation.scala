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

  for i <- 0 until 1 do
    addGameObject(Herbivore(Transform(Position(340, 240).randomWithin(300), 0),
      CreatureAttributes(100, 10, 150, 140, 2, 0.3)
    ))

  for i <- 0 to 10000 do
    addGameObject(Plant(Transform(Position(340, 240).randomWithin(500), 0), 100, 100))

  def addGameObject(gameObject: GameObject): Unit =
    gameObjects += gameObject

  def removeGameObject(gameObject: GameObject): Unit =
    gameObjects -= gameObject

  def addCommand(command: SimulationCommand): Unit =
    commands += command

  def minBy[T <: GameObject](f: GameObject => Double): Option[T] =
    gameObjects.collect { case v: T => v }.minByOption(f)

  def tick(deltaTime: Int): Unit =
    gameObjects.foreach(_.tick(deltaTime, this))
    println(gameObjects.length)
    commands.foreach(_(this))
    commands.clear()



    



