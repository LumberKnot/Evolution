package creature

import controler.Position

case class Plant(position: Position, reproductionTime : Int, reproductionRange : Double):

  private var time : Double = 0

  def tick() : Unit =
    time += 1

    if time >= reproductionTime then
      time = 0
      reproduce()


  private def reproduce() : Unit =
    copy(position = position.randomWithin(reproductionRange))


