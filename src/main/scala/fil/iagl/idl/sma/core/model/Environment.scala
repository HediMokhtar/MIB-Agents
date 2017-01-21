package fil.iagl.idl.sma.core.model

import fil.iagl.idl.sma.core.view.AgentsView

import scalafx.geometry.Rectangle2D
import scalafx.stage.Screen

/**
  * 2 dimensions Board for a multi-agent system
  */

trait Environment{

  var height: Int
  var width: Int

  //Check if the dimensions are reasonable
  this.setValideDimension

  val agentsEnvironment: Array[Array[Agent]] = Array.ofDim[Agent](height,width)
  val agents: List[Agent]

  val isToric : Boolean

  def getContent(x : Int, y: Int): Agent = {
    agentsEnvironment(x)(y)
  }

  /**
    * Create a agent to the specific place and link it to the view
    */
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

  def setValideDimension {
    val bounds: Rectangle2D = Screen.primary.bounds
    if (this.width <= 500) {
      this.width = bounds.getWidth.toInt
    }
    if (this.height <= 500) {
      this.height = bounds.getHeight.toInt
    }
  }

}
