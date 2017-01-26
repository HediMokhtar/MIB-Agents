package fil.iagl.idl.sma.wator.model

import javafx.scene.paint.Color

import fil.iagl.idl.sma.core.model.Environment
import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Neighborhood, Position}
import fil.iagl.idl.sma.wator.util.WatorMetricsData

import scala.util.Random

class Shark(breed: Int, val starve: Int, val environment: Environment) extends WatorAgent(breed) {

  agentType = AgentType.SHARK

  var starvationCounter = 0

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    if(age == 1)
      agentsShapes.recolor(this, Color.RED)
    else
    if(age == 0)
        agentsShapes.recolor(this, Color.PINK)
    if (starvationCounter == starve) {
      agentsShapes.removeAgent(this)
      environment.unmark(position.x, position.y)
      WatorMetricsData.decrementNSharks()
    }
    else {
      val nextPotentialFishPosition = nextPositionOccupiedByAFish(environment)
      nextPotentialFishPosition match {
        case Some(pos) => {
          val fishToBeEaten = environment.getAgent(pos.x, pos.y).get
          agentsShapes.removeAgent(fishToBeEaten)
          environment.unmark(fishToBeEaten.position.x, fishToBeEaten.position.y)
          fishToBeEaten.isVisited = true
          WatorMetricsData.decrementNFishes()
          val potentialChild = Shark(breed, starve, environment)
          moveAndAimToReproduce(environment, potentialChild, Color.PINK, agentsShapes)
          position = pos
          agentsShapes.relocateShape(this, position.x * 4, position.y * 4)
          environment.mark(position.x, position.y, this)
          starvationCounter = 0
        }
        case None => {
          val nextPotentialFreePosition = nextFreePosition(environment)
          nextPotentialFreePosition match {
            case Some(x) => {
              val potentialChild = Shark(breed, starve, environment)
              moveAndAimToReproduce(environment, potentialChild, Color.PINK, agentsShapes)
              position = nextPotentialFreePosition.get
              agentsShapes.relocateShape(this, position.x * 4, position.y * 4)
              environment.mark(position.x, position.y, this)
              starvationCounter += 1
            }
            case None => {
              starvationCounter += 1
              breedCounter += 1
              age += 1
            }
          }
        }
      }
    }
  }

  def nextPositionOccupiedByAFish(environment: Environment): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      environment.getAgent(newPosition.x, newPosition.y) match {
        case Some(agent) if agent.agentType == AgentType.FISH => return Some(newPosition)
        case _ => ()
      }
    }))
    None
  }
}

object Shark {

  def apply(breed: Int, starve: Int, environment: Environment) = new Shark(breed, starve, environment)

}
