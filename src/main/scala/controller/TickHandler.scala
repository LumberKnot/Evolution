package controller

case class TickHandler(timePerTick : Int = 16):

  private var objects : Vector[Clockable] = Vector()

  def addClockable(clockable: Clockable) : Unit =
    objects = objects :+ clockable


  def sendTick(deltaTime : Int) : Unit =
    objects.foreach(_.tick(deltaTime))