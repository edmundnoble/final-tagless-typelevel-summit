trait Storage {
  def get(key: String): Option[String]

  def put(key: String, data: String): Unit

  def remove(key: String): Unit
}

final class MapStorage(initial: Map[String, String] = Map.empty) extends Storage {
  var currentMap: Map[String, String] = initial

  def get(key: String): Option[String] = currentMap.get(key)

  def put(key: String, data: String): Unit = {
    currentMap += key -> data
  }

  def remove(key: String): Unit = {
    currentMap -= key
  }
}