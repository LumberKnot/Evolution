package simulation

import controller.{Drawable, Position}
import window.Window

import java.awt.event.KeyEvent

case class SimulationController(width: Int, height: Int):
  val window: Window = Window("Simulation Viewer", width, height)
  val simulation: Simulation = Simulation()
  var currentPosition: Position = Position(0, 0)

  val deltaTime = 5

  def run(): Unit =
    while true do
      simulation.tick(deltaTime)
      tickCamera()
      window.render(g2d => {
        simulation.gameObjects.foreach(creature => creature.draw(g2d))
      })
      Thread.sleep(10)



  private def tickCamera(): Unit =
    if window.keyManager.isKeyPressed(KeyEvent.VK_UP) then currentPosition = currentPosition + Position(0, 10)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_DOWN) then currentPosition = currentPosition + Position(0, -10)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_LEFT) then currentPosition = currentPosition + Position(5, 0)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_RIGHT) then currentPosition = currentPosition + Position(-10, 0)

    window.setOrigin(currentPosition)
    //println(s"Camera offset = $currentPosition, MousePos = ${window.mousePos}")
