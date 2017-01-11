package fil.iagl.idl.sma.core.model

import fil.iagl.idl.sma.particles.model.Agent

class Environment(val width: Int, val height: Int) {

  var agents = Array.ofDim[Option[Agent]](width, height)

  for (i <- 0 until width; j <- 0 until height)
    agents(i)(j) = None

  def getAgent(x: Int, y: Int): Option[Agent] = agents(x)(y)

}

object Environment {

  def apply(width: Int, height: Int) = new Environment(width, height)


}
