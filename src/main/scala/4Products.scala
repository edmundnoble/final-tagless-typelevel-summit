import cats.~>

trait Product0[A, B] {
  def first[C](consumer: A => C): C
  def second[C](consumer: B => C): C
}

trait Product1[F[_], G[_], A] {
  def first[H[_]](consumer: F ~> H): H[A]
  def second[H[_]](consumer: G ~> H): H[A]
}
