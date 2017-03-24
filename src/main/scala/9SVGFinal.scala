trait SVGFinal[A] {
  type Paths
  def drawCircle(x: Double, y: Double, r: Double) : A
  def drawEllipse(x: Double, y: Double, r: Double) : A
  val path: PathFinal[Paths]
  def drawPath(components: Paths): A
}

trait PathFinal[A] {
  def moveTo(x: Double, y: Double): A
  def lineTo(x: Double, y: Double): A
  def lineToRel(dx: Double, dy: Double): A
}
