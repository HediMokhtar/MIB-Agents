package fil.iagl.idl.sma.core.util

import scala.util.Random

class MooreNeighborhood extends Neighborhood {

  override def neighborPositions(position: Position): List[Position] =
    Random.shuffle(List(
      new Position(position.x + 1, position.y),
      new Position(position.x + 1, position.y + 1),
      new Position(position.x, position.y + 1),
      new Position(position.x - 1, position.y + 1),
      new Position(position.x - 1, position.y),
      new Position(position.x - 1, position.y - 1),
      new Position(position.x, position.y - 1),
      new Position(position.x + 1, position.y)
    ))

}
