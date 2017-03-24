import cats.data.State

trait StorageFinal[F[_]] {
  def get(key: String): F[Option[String]]

  def put(key: String, data: String): F[Unit]

  def remove(key: String): F[Unit]
}

final class MapStorageFinal
  extends StorageFinal[State[Map[String, String], ?]] {
  def get(key: String): State[Map[String, String], Option[String]] =
    State.inspect(_.get(key))

  def put(key: String, data: String): State[Map[String, String], Unit] =
    State.modify(_ + (key -> data))

  def remove(key: String): State[Map[String, String], Unit] =
    State.modify(_ - key)
}

