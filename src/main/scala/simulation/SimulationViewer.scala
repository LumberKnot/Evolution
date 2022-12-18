package simulation

import controller.{Drawable, Position}
import window.Window

import java.awt.event.KeyEvent

case class SimulationViewer(width: Int, height: Int):
  val window = Window("Simulation Viewer", width, height)
  val simulation = Simulation()
  var currentPosition = Position(0, 0)


  def run(): Unit =
    while true do
      simulation.tick(5)
      tickCamera()
      window.render { g2d =>
        simulation.gameObjects.foreach {
          case creature: Drawable => creature.draw(g2d)
          case _ => println("WOOP")
        }
      }

  private def tickCamera(): Unit =
    if window.keyManager.isKeyPressed(KeyEvent.VK_UP) then currentPosition = currentPosition + Position(0, 10)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_DOWN) then currentPosition = currentPosition + Position(0, -10)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_LEFT) then currentPosition = currentPosition + Position(5, 0)
    else if window.keyManager.isKeyPressed(KeyEvent.VK_RIGHT) then currentPosition = currentPosition + Position(-10, 0)

    window.setOrigin(currentPosition)
    Thread.sleep(10)
    println(s"Camera offset = $currentPosition, MousePos = ${window.mousePos}")
