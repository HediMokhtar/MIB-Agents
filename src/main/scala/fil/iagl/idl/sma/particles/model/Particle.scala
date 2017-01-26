package fil.iagl.idl.sma.particles.model

import fil.iagl.idl.sma.core.model.{Agent, Environment}
import fil.iagl.idl.sma.core.util.{AgentsShapes, Neighborhood, Position}

import scala.util.Random

class Particle(val toroidal: Boolean, val environment: Environment) extends Agent {

  var stepX = 0
  var stepY = 0
  randomDirection()

  def randomDirection(): Unit = {
    stepX = choices(Random.nextInt(3))
    stepY = choices(Random.nextInt(3))
    do {
      stepX = choices(Random.nextInt(3))
      stepY = choices(Random.nextInt(3))
    } while (stepX == 0 && stepY == 0)
  }

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    val newPosition = getNextPosition(environment)
    if (positionIsEmpty(newPosition, environment)) {
      position = newPosition
    } else {
      position = changeDirection(newPosition, environment)
    }
    agentsShapes.relocateShape(this, position.x * 5, position.y * 5)
  }

  def getNextPosition(environment: Environment): Position = {
    if (toroidal) {
      val newX = if ((position.x + stepX) >= 0) position.x + stepX else (position.x + stepX) + environment.width
      val newY = if ((position.y + stepY) >= 0) position.y + stepY else (position.y + stepY) + environment.height
      Position(newX % environment.width, newY % environment.height)
    }
    else {
      var newX = position.x + stepX
      var newY = position.y + stepY

      if ((newX < 0) || (newX >= environment.width)) {
        stepX = -stepX
        newX = position.x + stepX
      }

      if ((newY < 0) || (newY >= environment.height)) {
        stepY = -stepY
        newY = position.y + stepY
      }
      Position(newX, newY)
    }
  }

  def positionIsEmpty(newPosition: Position, environment: Environment): Boolean = environment.getAgent(newPosition.x, newPosition.y).isEmpty

  def changeDirection(position: Position, environment: Environment): Position = {
    var newPosition = position
    do {
      randomDirection()
      newPosition = getNextPosition(environment)
    } while (((newPosition.x != position.x) && (newPosition.y != position.y)) && (!positionIsEmpty(position, environment)))
    newPosition
  }

}
object Particle {

  def apply(toroidal: Boolean, environment: Environment) = new Particle(toroidal, environment)

}
