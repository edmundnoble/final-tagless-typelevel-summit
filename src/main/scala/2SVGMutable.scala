trait SVGRenderer {
  def circle(x: Double, y: Double, r: Double): Unit

  def ellipse(x: Double, y: Double, r: Double): Unit

  def newPathRenderer: PathRenderer

  def includePath(renderer: PathRenderer): Unit
}

trait PathRenderer {
  def moveTo(x: Double, y: Double): Unit

  def lineTo(x: Double, y: Double): Unit

  def lineToRelative(x: Double, y: Double): Unit
}

