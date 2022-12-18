package controller

import simulation.Simulation

trait Clockable:
  def tick(deltaTime : Int, simulation: Simulation) : Unit