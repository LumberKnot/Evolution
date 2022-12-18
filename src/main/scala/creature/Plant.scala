package creature

import controller.{Clockable, Position, TickHandler}

case class Plant(position: Position, reproductionTime : Int, reproductionRange : Double) extends Clockable:

  private var time: Int = 0

  override def tick(deltaTime : Int) : Unit =
    time += 1

    if time >= reproductionTime then
      time = 0
      reproduce()


  private def reproduce() : Unit =
    copy(position = position.randomWithin(reproductionRange))


