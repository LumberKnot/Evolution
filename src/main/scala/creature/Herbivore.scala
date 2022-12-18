package creature

import controller.{Clockable, Position}

case class Herbivore(position: Position, attributes: CreatureAttributes) extends Clockable:
  override def tick(): Unit = ???


