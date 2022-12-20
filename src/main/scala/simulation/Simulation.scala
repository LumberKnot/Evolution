package simulation

import controller.*
import creature.*
import window.Window

import scala.collection.mutable.ArrayBuffer

case class Simulation():

  private var _gameTime: Int = 0
  def gameTime: Int = _gameTime
  val gameObjects: ArrayBuffer[GameObject] = ArrayBuffer.empty[GameObject]
  for i <- 0 to 10 do
    addGameObject(Herbivore(Transform(Position(50, 50).randomWithin(100), 0), CreatureAttributes(100, 150, 75, 2, 4)))

  for i <- 0 to 100 do
    addGameObject(Plant(Transform(Position(340, 240).randomWithin(300), 0), 100, 100))

  private def addGameObject(gameObject: GameObject): Unit =
    gameObjects += gameObject


  def tick(deltaTime: Int): Unit =
    gameObjects.foreach(_.tick(deltaTime, this))


    



