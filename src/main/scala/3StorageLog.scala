import cats.data.WriterT
import cats.free.Free
import cats.~>

sealed trait StorageAction[A]
case class Get(key: String) extends StorageAction[Option[String]]
case class Put(key: String, data: String) extends StorageAction[Unit]
case class Remove(key: String) extends StorageAction[Unit]

type StorageProgram[A] = Free[StorageAction, A]

def logRemovedKeys: StorageAction ~> WriterT[StorageProgram, String, ?] =
  new (StorageAction ~> WriterT[StorageProgram, String, ?]) {
  override def apply[A](fa: StorageAction[A]): WriterT[StorageProgram, Vector[String], A] = fa match {
    case Get(k) => WriterT.lift[StorageProgram, Vector[String], Unit](Free.liftF(Get(k)))
    case Put(k, d) => WriterT.lift[StorageProgram, Vector[String], Unit](Free.liftF(Put(k, d)))
    case Remove(k) => WriterT.putT[StorageProgram, Vector[String], Unit](Free.liftF(Remove(k)))(Vector(k))
  }
}