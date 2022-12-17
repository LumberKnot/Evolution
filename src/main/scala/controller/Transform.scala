package controller
type Radians = Double
case class Transform(position: Position, rotation: Radians):
  def moveTo(position: Position): Unit = copy(position)
  override def toString: String = s"Transform($position, ${math.toDegrees(rotation)})"

object Transform:
  def apply(x: Double, y: Double, rotation: Radians): Transform = Transform(Position(x, y), rotation)
