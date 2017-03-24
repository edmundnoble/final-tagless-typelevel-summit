sealed trait SVGAlgebra
case class DrawCircle(x: Double, y: Double, r: Double) extends SVGAlgebra
case class DrawEllipse(x: Double, y: Double, r: Double) extends SVGAlgebra
case class DrawPath(components: List[PathAlgebra]) extends SVGAlgebra
type SVGProgram = List[SVGAlgebra]

sealed trait PathAlgebra
case class MoveTo(x: Double, y: Double) extends PathAlgebra
case class LineTo(x: Double, y: Double) extends PathAlgebra
case class LineToRel(dx: Double, dy: Double) extends PathAlgebra
type PathProgram = List[PathAlgebra]

