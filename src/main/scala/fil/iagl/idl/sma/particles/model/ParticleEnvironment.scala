package fil.iagl.idl.sma.particles.model


import javafx.scene.shape.Circle

import fil.iagl.idl.sma.core.model.{Agent, AgentsShapes, Environment}

import scala.util.Random

class ParticleEnvironment(val height: Int,
                          val width : Int,
                          val agentsNumber: Int,
                          val agentsSize: Double,
                          val toric: Boolean) extends Environment{

  //Create all the agents
  val agents: List[Particle] = List.fill(agentsNumber)(Particle(Random.nextInt(height),Random.nextInt(width)))

  val agentsShapes = AgentsShapes()


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
  def move(particle: Particle, nextX: Int, nextY: Int): Unit = {
    this.deleteContent(particle.x,particle.y)
    this.setContent(nextX, nextY, particle)
    particle.x = nextX
    particle.y = nextY
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
    if(isAvailable(particle.x,particle.y)) {
      setContent(particle.x, particle.y, particle)
      val particleShape = new Circle(agentsSize, particle.color)
      particleShape.relocate(particle.x * 5, particle.y * 5)
      agentsShapes.linkAgentToShape(particle, particleShape)
    }
    else{
      particle.reroll(height,width)
      insertAgent(particle)
    }
  }

  def nextState(): Unit ={
    moveAgents(agents)
  }

  def moveAgents(agentsList : List[Particle]):Unit ={
    if(agentsList.isEmpty)
      println("Empty list")
    else {
      val currentAgent = agentsList.head
      val next = currentAgent.getNextCoordinates(this)
      setContent(next._1,next._2,currentAgent)
      moveAgents(agentsList.tail)
    }
  }

}
