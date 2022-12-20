package creature

case class CreatureAttributes(
                             //General
                              startEnergy : Double,
                              size : Double,

                             //reproduction
                              reproduceEnergy : Double,
                              reproduceCost : Double,

                             //Movement
                             speed : Double,
                             movementCost : Double,
                             )
