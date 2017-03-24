import cats.data.State
import cats.free.Free
import cats.~>

sealed trait StorageAction[A]
case class Get(key: String) extends StorageAction[Option[String]]
case class Put(key: String, data: String) extends StorageAction[Unit]
case class Remove(key: String) extends StorageAction[Unit]

type StorageProgram[A] = Free[StorageAction, A]

def interpretStorageProgramPurely[A](program: StorageProgram[A]): State[Map[String, String], A] =
  program.foldMap(new (StorageAction ~> State[Map[String, String], ?]) {
    override def apply[X](fa: StorageAction[X]): State[Map[String, String], X] =
      fa match {
        case Get(key) =>
          State.inspect[Map[String, String], Option[String]](_.get(key))
        case Put(key, data) =>
          State.modify[Map[String, String]](_ + (key -> data))
        case Remove(key) => State.modify[Map[String, String]](_ - key)
      }
  })