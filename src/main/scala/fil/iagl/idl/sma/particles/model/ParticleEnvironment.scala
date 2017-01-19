package fil.iagl.idl.sma.particles.model

import fil.iagl.idl.sma.core.model.Environment

import scala.util.Random

class ParticleEnvironment(val height: Int,
                          val width : Int,
                          val agentsNumber: Int,
                          val agentsSize: Double,
                          val isToric: Boolean) extends Environment{

  val agents: List[Particle] = List.fill(agentsNumber)(new Particle(Random.nextInt(height),Random.nextInt(width),this))

  /**
    * Create and allocate all the agents in the 2D Array
    */
  override def init(): Unit = {
    agents.map(insertAgent)
  }

  def insertAgent(particle: Particle): Unit = {
    if(isAvailable(particle.x,particle.y)) {
      setContent(particle.x, particle.y, particle)
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
