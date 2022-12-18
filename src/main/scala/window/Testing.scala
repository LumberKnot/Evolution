package window

import controller.Position
import java.awt.event.KeyEvent
object Testing:
  @main
  def main: Unit =
    val (width, height) = (1366, 768)
    val window = Window("Name", width, height)
    window.setOrigin(Position(100, 0))
    var currentPosition = Position(0,0)
    while true do
      window.render {g2d =>
        for i <- 0 to width by 50 do
          g2d.fillRect(0, 0, 10, 10)
          g2d.drawLine(i, 0, i, height)
      }
      if window.keyManager.isKeyPressed(KeyEvent.VK_UP) then currentPosition = currentPosition + Position(0, 10)
      else if window.keyManager.isKeyPressed(KeyEvent.VK_DOWN) then currentPosition = currentPosition + Position(0, -10)
      else if window.keyManager.isKeyPressed(KeyEvent.VK_LEFT) then currentPosition = currentPosition + Position(5, 0)
      else if window.keyManager.isKeyPressed(KeyEvent.VK_RIGHT) then currentPosition = currentPosition + Position(-10, 0)
      window.setOrigin(currentPosition)
      Thread.sleep(10)
      println(s"Camera offset = $currentPosition, MousePos = ${window.mousePos}")

