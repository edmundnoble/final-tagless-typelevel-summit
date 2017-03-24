
import cats.data.WriterT
import cats.{Applicative, Monad}
import cats.implicits._

trait StorageFinal[F[_]] {
  def get(key: String): F[Option[String]]

  def put(key: String, data: String): F[Unit]

  def remove(key: String): F[Unit]
}

class LogRemovedKeys[F[_]: Applicative](storage: StorageFinal[F])
  extends StorageFinal[WriterT[F, Vector[String], ?]] {
  def get(key: String): WriterT[F, Vector[String], Option[String]] =
    WriterT.lift(storage.get(key))
  def put(key: String, data: String): WriterT[F, Vector[String], Unit] =
    WriterT.lift(storage.put(key, data))
  def remove(key: String): WriterT[F, Vector[String], Unit] =
    WriterT.putT(storage.remove(key))(Vector(key))
}

object StorageFinal {
  def putIfAbsent[F[_]: Monad](key: String, data: String)(implicit storage: StorageFinal[F]): F[Unit] =
    for {
      currentData <- storage.get(key)
      _ <- if (currentData.isDefined) ().pure[F] else storage.put(key, data)
    } yield ()
}