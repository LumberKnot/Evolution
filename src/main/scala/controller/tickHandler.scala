package controller

case class tickHandler(timePerTick : Int = 16):

  private var objects : Vector[Clockable] = Vector()

  def addClockable(clockable: Clockable) : Unit =
    objects = objects :+ clockable


  def sendTick() : Unit =
    objects.foreach(_.tick())