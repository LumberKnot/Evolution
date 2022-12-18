package controller
case class Transform(position: Position, rotation: Radians):
  def moveTo(position: Position): Transform = copy(position)
  def moved(dPosition: Position): Transform = copy(position + dPosition)
  def rotateTo(rotation: Radians): Transform = copy(rotation = rotation)
  override def toString: String = s"Transform($position, ${math.toDegrees(rotation)})"

object Transform:
  def apply(x: Int, y: Int, rotation: Radians): Transform = new Transform(Position(x, y), rotation)
