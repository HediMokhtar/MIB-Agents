package fil.iagl.idl.sma.core.model

/**
  * 2 dimensions Board for any multi-agent system
  */

trait Environment{


  val height: Int
  val width: Int
  val agentsEnvironment: Array[Array[Agent]] = Array.ofDim[Agent](height,width)
  val toric : Boolean

  def getContent(x : Int,y: Int): Agent

  def setContent(x : Int,y : Int, agent: Agent ): Unit

  def deleteContent(x : Int, y: Int): Unit

  def isAvailable(x:Int, y:Int): Boolean

  def init(): Unit

}
