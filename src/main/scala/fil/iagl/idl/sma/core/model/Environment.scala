package fil.iagl.idl.sma.core.model


class Environment(val width: Int, val height: Int) {

  var agents = Array.ofDim[Option[Agent]](width, height)

  for (i <- 0 until width; j <- 0 until height) {
    agents(i)(j) = None
  }

  def getAgent(x: Int, y: Int): Option[Agent] = agents(x)(y)

  def isFree(x: Int, y:Int): Boolean = agents(x)(y).isEmpty

  def mark(x: Int, y: Int, agent: Agent): Unit = agents(x)(y) = Some(agent)

  def unmark(x: Int, y: Int): Unit = agents(x)(y) = None

}


object Environment {

  def apply(width: Int, height: Int) = new Environment(width, height)

}


