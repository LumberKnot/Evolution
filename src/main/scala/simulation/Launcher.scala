package simulation

case object Launcher:
  @main
  def run(): Unit =
    SimulationController(680, 480).run()
