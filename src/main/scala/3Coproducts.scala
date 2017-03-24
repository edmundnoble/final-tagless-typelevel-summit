import cats.~>

trait Coproduct0[A, B] {
  def eliminate[C](firstChoice: A => C, secondChoice: B => C): C
}

trait Coproduct1[F[_], G[_], A] {
  def eliminate[H[_]](firstChoice: F ~> H, secondChoice: G ~> H): H[A]
}