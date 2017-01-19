package fil.iagl.idl.sma.core.model

/**
  * 2 dimensions Board for a multi-agent system
  */

trait Environment{

  val height: Int
  val width: Int
  val agentsEnvironment: Array[Array[Agent]] = Array.ofDim[Agent](height,width)
  val agents: List[Agent]

  val isToric : Boolean

  def getContent(x : Int, y: Int): Agent = {
    agentsEnvironment(x)(y)
  }

  def setContent(x: Int, y: Int, agent: Agent): Unit ={
    agentsEnvironment(x)(y) = agent
  }

  def deleteContent(x: Int, y: Int): Unit = {
    agentsEnvironment(x)(y) = null
  }

  def isAvailable(x: Int, y: Int): Boolean ={
    agentsEnvironment(x)(y) == null
  }

  /**
    * move an agent taking the destination in parameters
    */
  def move(nextX: Int, nextY: Int, agent: Agent): Unit = {
    deleteContent(agent.x,agent.y)
    setContent(nextX, nextY, agent)
    agent.setCoordinates(nextX,nextY)
  }

  def init(): Unit

  /**
    * Launch the environment
    */
  def run(): Unit

}
