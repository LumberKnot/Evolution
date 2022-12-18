package simulation

case object Launcher:
  @main
  def run(): Unit =
    SimulationViewer(680, 480).run()
