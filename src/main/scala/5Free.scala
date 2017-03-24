import cats.~>

trait Free0[T[_], F, A] {
  val freelyGeneratedTypeclass: T[F]

  def retract(value: F)(implicit tc: T[A]): A

  def foldMap[B](value: F)(f: A => B)(implicit tc: T[B]): B
}

trait Free1[T[_[_]], V[_], F[_]] {
  val freelyGeneratedTypeclass: T[V]

  def retract[A](fv: V[A])(implicit ev: T[F]): F[A]

  def foldMap[A, G[_]](fv: V[A])(trans: F ~> G)(implicit ev: T[G]): G[A]
}
