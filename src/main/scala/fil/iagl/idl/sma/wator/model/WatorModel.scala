package fil.iagl.idl.sma.wator.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.{Environment, Model}
import fil.iagl.idl.sma.core.util.{MooreNeighborhood, Observable, Position}

import scala.collection.mutable.ListBuffer
import scala.util.Random

class WatorModel(val width: Int,
                 val height: Int,
                 val nbFishes: Int,
                 val nbSharks: Int,
                 val fishBreedTime: Int,
                 val sharkBreedTime: Int,
                 val sharkStarveTime: Int) extends Model with Observable {

  val environment = Environment(width, height)
  val alreadyTakenPositions = ListBuffer[Position]()

  def init(): Unit = {
    for (i <- 0 until nbFishes) {
      val fish = Fish(fishBreedTime, environment)
      fish.position = Position(Random.nextInt(width), Random.nextInt(height))
      alreadyTakenPositions += fish.position
      val fishShape = new Circle(2.5, Color.GREEN)
      fishShape.relocate(fish.position.x * 4, fish.position.y * 4)
      agentsShapes.linkAgentToShape(fish, fishShape)
      environment.mark(fish.position.x, fish.position.y, fish)
      agents += fish
    }

    for (i <- 0 until nbSharks) {
      val shark = Shark(sharkBreedTime, sharkStarveTime, environment)
      shark.position = Position(Random.nextInt(width), Random.nextInt(height))
      alreadyTakenPositions += shark.position
      val sharkShape = new Circle(2.5, Color.PINK)
      sharkShape.relocate(shark.position.x * 4, shark.position.y * 4)
      agentsShapes.linkAgentToShape(shark, sharkShape)
      environment.mark(shark.position.x, shark.position.y, shark)
      agents += shark
    }
    agents = Random.shuffle(agents)
  }

  override def run(): Unit = {
    agents.foreach(agent => {
      if (!agent.isVisited) {
        agent.doIt(new MooreNeighborhood(), agentsShapes)
      }
    })
    val newAgents = agentsShapes.agentsToShapesAssociations.keySet &~ agents
    val deletedAgents = agents &~ agentsShapes.agentsToShapesAssociations.keySet
    agents = agents --= deletedAgents
    agents = agents ++= newAgents
    val deletedShapes = agentsShapes.shapesToDelete
    notifyObservers(newAgents, deletedShapes)
    agentsShapes.deleteShapesToDelete()
  }
}
