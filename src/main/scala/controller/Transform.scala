package controller
case class Transform(position: Position, rotation: Radians):
  def moveTo(position: Position): Transform = copy(position)
  def moved(dPosition: Position): Transform = copy(position + dPosition)
  def rotateTo(rotation: Radians): Transform = copy(rotation = rotation)
  override def toString: String = s"Transform($position, ${math.toDegrees(rotation)})"

  def angleTo(other: Transform): Radians =
    val Position(x, y) = this.position - other.position
    if x < 0 then
      math.atan(y / x.toDouble) + math.Pi
    else
      math.atan(y / x.toDouble)

object Transform:
  def apply(x: Int, y: Int, rotation: Radians): Transform = new Transform(Position(x, y), rotation)
