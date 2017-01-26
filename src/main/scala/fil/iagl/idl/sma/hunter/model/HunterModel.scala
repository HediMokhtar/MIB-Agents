package fil.iagl.idl.sma.hunter.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.{Environment, Model}
import fil.iagl.idl.sma.core.util.{MooreNeighborhood, Position}
import fil.iagl.idl.sma.hunter.util.DijkstraPathFinder

import scala.util.Random

class HunterModel(val nbHunters: Int, val nbObstacles: Int, envHeight: Int, envWidth: Int, agentSize: Double) extends Model {

  val environment = Environment(envWidth, envHeight)
  val dijkstraPathFinder = DijkstraPathFinder()
  val avatar = Avatar(environment)

  override def init(): Unit = {
    for (i <- 0 until nbHunters) {
      val hunter = Hunter(environment, dijkstraPathFinder)
      hunter.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      val hunterShape = new Circle(agentSize, Color.RED)
      hunterShape.relocate(hunter.position.x * 5, hunter.position.y * 5)
      agentsShapes.linkAgentToShape(hunter, hunterShape)
      environment.mark(hunter.position.x, hunter.position.y, hunter)
      agents += hunter
    }

    for (i <- 0 until nbObstacles) {
      val obstacle = Obstacle(environment)
      obstacle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      val obstacleShape = new Circle(agentSize, Color.ROSYBROWN)
      obstacleShape.relocate(obstacle.position.x * 5, obstacle.position.y * 5)
      agentsShapes.linkAgentToShape(obstacle, obstacleShape)
      environment.mark(obstacle.position.x, obstacle.position.y, obstacle)
    }

    avatar.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    val avatarShape = new Circle(agentSize, Color.BLUE)
    avatarShape.relocate(avatar.position.x * 5, avatar.position.y * 5)
    agentsShapes.linkAgentToShape(avatar, avatarShape)
    environment.mark(avatar.position.x, avatar.position.y, avatar)
    agents += avatar
  }


  override def run(): Unit = {
    dijkstraPathFinder.updateDijkstraDistances(environment, avatar.position)
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(new MooreNeighborhood(), agentsShapes)
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }
}
