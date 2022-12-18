package controller

import java.awt.Graphics2D

trait Drawable {
  def draw(g2d: Graphics2D): Unit
}
