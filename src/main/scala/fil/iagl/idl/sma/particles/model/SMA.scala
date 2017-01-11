package fil.iagl.idl.sma.particles.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.Environment
import fil.iagl.idl.sma.core.util.{AgentsShapes, Position}

import scala.collection.mutable.ListBuffer
import scala.util.Random

class SMA(val nbParticles: Int,
          val envWidth: Int,
          val envHeight: Int,
          val agentSize: Double,
          val toroidal: Boolean,
          val equity: Int
                    ) {

  var agents = ListBuffer[Agent]()
  val agentsShapes = AgentsShapes()
  val environment = Environment(envWidth, envHeight)

  def init(): Unit = {
    for (i <- 0 until nbParticles) {
      val agent = Agent(toroidal, environment, new Color(0.5, 0.5, 0.5, 1))
      agent.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      val particleShape = new Circle(agentSize, agent.color)
      particleShape.relocate(agent.position.x, agent.position.y)
      agentsShapes.linkAgentToShape(agent, particleShape)
      agents += agent
    }

    if (equity == 1)
      agents = Random.shuffle(agents)
  }

  def run(): Unit = {
    if (equity == 1)
      agents = Random.shuffle(agents)
    if (equity == 2) {
      val rand = new Random()
      val agent = agents(rand.nextInt(agents.size))
      agent.update(agentsShapes)
    }
    else
    agents.foreach(agent => {
      agent.update(agentsShapes)
    })

  }

}
