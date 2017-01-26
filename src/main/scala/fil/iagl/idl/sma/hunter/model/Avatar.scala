package fil.iagl.idl.sma.hunter.model

import fil.iagl.idl.sma.core.model.{Agent, Environment}
import fil.iagl.idl.sma.core.util._
import fil.iagl.idl.sma.hunter.util.HunterCommandOptions

class Avatar(environment: Environment) extends Agent {

  agentType = AgentType.AVATAR
  var direction = Direction.EAST
  var stepX = 0
  var stepY = 0

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    getDirection()
    if(HunterCommandOptions.logger)
      println("direction : " + direction)
    position = getNextPosition(environment)
    agentsShapes.relocateShape(this, position.x * 5, position.y * 5)
  }

  def getDirection(): Unit = {
    direction match {
      case Direction.NORTH =>
        stepX = 0
        stepY = -1
      case Direction.EAST =>
        stepX = 1
        stepY = 0
      case Direction.SOUTH =>
        stepX = 0
        stepY = 1
      case Direction.WEST =>
        stepX = -1
        stepY = 0
      case _ => ()
    }
  }

  def getNextPosition(environment: Environment): Position = {
    val nextX = if ((position.x + stepX) >= 0) position.x + stepX else (position.x + stepX) + environment.width
    val nextY = if ((position.y + stepY) >= 0) position.y + stepY else (position.y + stepY) + environment.height
    val nextPosition = Position(nextX % environment.width, nextY % environment.height)
    if (environment.isFree(nextPosition.x, nextPosition.y))
      nextPosition
    else
      position
  }

}

object Avatar {

  def apply(environment: Environment) = new Avatar(environment)

}