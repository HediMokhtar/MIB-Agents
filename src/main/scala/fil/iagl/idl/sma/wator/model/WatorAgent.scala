package fil.iagl.idl.sma.wator.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.{Agent, Environment}
import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Observable, Position}
import fil.iagl.idl.sma.wator.util.WatorMetricsData

import scala.util.Random

abstract class WatorAgent(val breed: Int) extends Agent with Observable {

  var breedCounter = 0

  var age = 0

  def nextFreePosition(environment: Environment): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      if (environment.isFree(newPosition.x, newPosition.y)) {
        return Some(newPosition)
      }
    }))
    None
  }

  def moveAndAimToReproduce(environment: Environment, potentialChild: Agent, color: Color, agentsShapes: AgentsShapes): Unit = {
    if (breedCounter == breed) {
      potentialChild.position = Position(position.x, position.y)
      val childShape = new Circle(2.5, color)
      childShape.relocate(position.x * 4, position.y * 4)
      agentsShapes.linkAgentToShape(potentialChild, childShape)
      environment.mark(potentialChild.position.x, potentialChild.position.y, potentialChild)
      if (agentType == AgentType.FISH) WatorMetricsData.incrementNFishes() else WatorMetricsData.incrementNSharks()
      breedCounter = 0
    }
    else {
      breedCounter += 1
      environment.unmark(position.x, position.y)
    }
    age += 1
  }

}
