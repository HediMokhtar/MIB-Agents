package fil.iagl.idl.sma.particles.model


import fil.iagl.idl.sma.core.model.{Agent, Environment}

import scala.util.Random

class ParticleEnvironment(val height: Int,
                          val width : Int,
                          val agentsNumber: Int) extends Environment{

  //Create all the agents
  val agents: List[Particle] = List.fill(agentsNumber)(Particle(Random.nextInt(height),Random.nextInt(width)))


  override def getContent(x: Int, y: Int): Agent = {
    agentsEnvironment(x)(y)
  }

  override def setContent(x: Int, y: Int, agent: Agent): Unit ={
    agentsEnvironment(x)(y) = agent
  }

  override def deleteContent(x: Int, y: Int): Unit = {
    agentsEnvironment(x)(y) = null
  }

  override def isAvailable(x: Int, y: Int): Boolean ={
    agentsEnvironment(x)(y) == null
  }


  /**
    * move an agent taking the destination in parameters
    */
  def move(particle: Particle, x: Int, y: Int): Unit = {
    this.setContent(x, y, particle)
    this.deleteContent(particle.x,particle.y)
  }

  /**
    * Create and allocate all the agents in the 2D Array
    */
  override def init(): Unit = {
    insertAgents(agents)
  }

  def insertAgents(listAgents : List[Particle]): Unit= {
    if (listAgents.isEmpty)
      println("Insertion done or there is no particles !")
    else{
      insertAgent(listAgents.head)
      insertAgents(listAgents.tail)
    }
  }

  def insertAgent(particle: Particle): Unit = {
    if(isAvailable(particle.x,particle.y))
      setContent(particle.x,particle.y,particle)
    else{
      particle.reroll(height,width)
      insertAgent(particle)
    }
  }
}
