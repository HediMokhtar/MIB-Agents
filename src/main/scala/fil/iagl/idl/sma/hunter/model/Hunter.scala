package fil.iagl.idl.sma.hunter.model

import javafx.application.Platform

import fil.iagl.idl.sma.core.model.{Agent, Environment}
import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Neighborhood, Position}
import fil.iagl.idl.sma.hunter.util.{DijkstraPathFinder, HunterCommandOptions}

import scala.util.Random

class Hunter(val environment: Environment, val dijkstraPathFinder: DijkstraPathFinder) extends Agent {

  agentType = AgentType.HUNTER

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))
    var lowestDijkstraValue = Int.MaxValue
    var optimalNextPosition: Option[Position] = None
    list1.foreach(i => list2.foreach(j => {
      val nextX = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val nextY = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val neighborPosition = Position(nextX % environment.width, nextY % environment.height)
      if (environment.isFree(neighborPosition.x, neighborPosition.y)) {
        val neighborDistance = dijkstraPathFinder.currentDijkstraDistancesOption.get(neighborPosition.x)(neighborPosition.y)
        if (neighborDistance < lowestDijkstraValue) {
          lowestDijkstraValue = neighborDistance
          optimalNextPosition = Some(neighborPosition)
          if(HunterCommandOptions.logger)
            println("distance optimale : " + optimalNextPosition)
        }
      }
      else if (environment.getAgent(neighborPosition.x, neighborPosition.y).get.agentType == AgentType.AVATAR) {
        if(HunterCommandOptions.logger)
          println("avatar hit, game over")
        Platform.exit()
      }
    }))
    position = optimalNextPosition.get
    agentsShapes.relocateShape(this, position.x * 5, position.y * 5)
  }

}

object Hunter {

  def apply(environment: Environment, dijkstraPathFinder: DijkstraPathFinder) = new Hunter(environment, dijkstraPathFinder)

}
