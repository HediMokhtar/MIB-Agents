package fil.iagl.idl.sma.particles.model

import scalafx.scene.shape.Circle
import fil.iagl.idl.sma.core.model.Environment
import fil.iagl.idl.sma.particles.view.ParticlesView

import scala.util.Random
import scalafx.scene.paint.Color

class ParticleEnvironment(var height: Int,
                          var width : Int,
                          val agentsNumber: Int,
                          val agentsSize: Double,
                          val isToric: Boolean) extends Environment{

  val agents: List[Particle] = List.fill(agentsNumber)(new Particle(Random.nextInt(height),Random.nextInt(width),this))


  val view = new ParticlesView(this)

  /**
    * Create and allocate all the agents in the 2D Array and the view
    */
  override def init(): Unit = {
    agents.map(insertAgent)
  }

  def insertAgent(particle: Particle): Unit = {
    if(isAvailable(particle.x,particle.y)) {
      setContent(particle.x, particle.y, particle)
      val particleViewElement = new Circle{
        centerX = particle.x
        centerY = particle.y
        fill = particle.color
        radius = agentsSize.toInt
      }
      view.agentsMaterialization(particle, particleViewElement)
    }
    else{ //case where the random coordinates is already pick by another agent
      particle.rerollCoordinates(height,width)
      insertAgent(particle)
    }
  }

  def nextState(): Unit ={
    agents.map(moveAgent)
  }

  def moveAgent(particle: Particle): Unit ={
    particle.doIt()
  }

  //todo
  override def run(): Unit= {
    nextState()
  }

}
