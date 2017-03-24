import cats.data.Writer
import org.atnos.eff.{Eff, |=}
import org.atnos.eff.writer

class LogRemovedKeys[FS](storage: StorageFinal[Eff[FS, ?]])
                        (implicit member: Writer[Vector[String], ?] |= FS)
  extends StorageFinal[Eff[FS, ?]] {
  def get(key: String): Eff[FS, Option[String]] =
    storage.get(key)
  def put(key: String, data: String): Eff[FS, Unit] =
    storage.put(key, data)
  def remove(key: String): Eff[FS, Unit] =
    writer.tell[FS, Vector[String]](Vector(key)) >> storage.remove(key)
}