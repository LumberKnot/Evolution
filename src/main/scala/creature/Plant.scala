package creature

import controller.{Clockable, Position, TickHandler}

case class Plant(position: Position, reproductionTime : Int, reproductionRange : Double, clock : TickHandler) extends Clockable:

  private var time: Int = 0
  clock.addClockable(this)

  def tick() : Unit =
    time += 1

    if time >= reproductionTime then
      time = 0
      reproduce()


  private def reproduce() : Unit =
    copy(position = position.randomWithin(reproductionRange))


