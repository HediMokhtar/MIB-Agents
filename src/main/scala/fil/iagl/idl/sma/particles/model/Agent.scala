package fil.iagl.idl.sma.particles.model

import javafx.scene.paint.Color

import fil.iagl.idl.sma.core.model.Environment
import fil.iagl.idl.sma.core.util.{AgentsShapes, Position}

import scala.util.Random

class Agent(val toroidal: Boolean, val environment: Environment, var color: Color) {

  var position = Position(0, 0)
  val choices = List(-1, 0, 1)
  var stepX = 0
  var stepY = 0
  randomDirection()

  def randomDirection(): Unit = {
    do {
      stepX = choices(Random.nextInt(3))
      stepY = choices(Random.nextInt(3))
    } while (stepX == 0 && stepY == 0)
  }

  def update(agentsShapes: AgentsShapes): Unit = {
    val newPosition = getNextPosition(environment)
    if (positionFree(newPosition, agentsShapes))
      position = newPosition
    else {
      position = changeDirection(newPosition, environment, agentsShapes)
      agentsShapes.recolor(this)
    }
    agentsShapes.relocateShape(this, position.x, position.y)
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

  def positionFree(position: Position, agentsShapes: AgentsShapes): Boolean = {
    var free = true;
    agentsShapes.agentsToShapesAssociations.keys.foreach(agent => if(agent.position.x == position.x && agent.position.y == position.y) free = false)
    free
  }

  def changeDirection(position: Position, environment: Environment, agentsShapes: AgentsShapes): Position = {
    var newPosition = position
    do {
      randomDirection()
      newPosition = getNextPosition(environment)
    } while (((newPosition.x != position.x) && (newPosition.y != position.y)) && (!positionFree(position, agentsShapes)))
    newPosition
  }

}

object Agent {

  def apply(toroidal: Boolean, environment: Environment, color: Color) = new Agent(toroidal, environment, color)

}
