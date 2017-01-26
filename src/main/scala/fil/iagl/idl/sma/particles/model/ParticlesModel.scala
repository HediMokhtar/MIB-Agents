package fil.iagl.idl.sma.particles.model

import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.{Environment, Model}
import fil.iagl.idl.sma.core.util.{MooreNeighborhood, Position}
import fil.iagl.idl.sma.particles.util.ParticlesRandomColorGenerator

import scala.util.Random

class ParticlesModel(val nbParticles: Int,
                     val envWidth: Int,
                     val envHeight: Int,
                     val agentSize: Double,
                     val toroidal: Boolean,
                     val equity: Boolean
                    ) extends Model {

  val environment = Environment(envWidth, envHeight)

  override def init(): Unit = {
    for (i <- 0 until nbParticles) {
      val particle = Particle(toroidal, environment)
      particle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      val particleShape = new Circle(agentSize, ParticlesRandomColorGenerator.randomColor())
      particleShape.relocate(particle.position.x * 5, particle.position.y * 5)
      agentsShapes.linkAgentToShape(particle, particleShape)
      environment.mark(particle.position.x, particle.position.y, particle)
      agents += particle
    }

    if (equity)
      agents = Random.shuffle(agents)
  }

  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(new MooreNeighborhood(), agentsShapes)
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }

}

object ParticlesModel {

  def apply(nbParticles: Int,
            envWidth: Int,
            envHeight: Int,
            agentSize: Double,
            speed: Int,
            toroidal: Boolean,
            equity: Boolean) = new ParticlesModel(nbParticles, envWidth, envHeight, agentSize, toroidal, equity)

}
