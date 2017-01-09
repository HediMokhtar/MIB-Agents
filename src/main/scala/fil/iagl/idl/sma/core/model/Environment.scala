package fil.iagl.idl.sma.core.model

/**
  * 2 dimensions Board for any multi-agent system
  */

trait Environment{


  val length: Int
  val width: Int
  val agentsEnvironment = Array.ofDim[Agent](length,width)

  def getContent(x : Int,y: Int): Agent

  def setContent(x : Int,y : Int, agent: Agent ): Unit

  def deleteContent(x : Int, y: Int): Unit

}
