package simulation

case object Launcher:
  @main
  def run(): Unit =
    SimulationControler(680, 480).run()
