package fil.iagl.idl.sma.particles.model


import fil.iagl.idl.sma.core.model.{Agent, Environment}

class ParticleEnvironment(val length: Int, val width : Int) extends Environment{

  override def getContent(x: Int, y: Int): Agent = {
    agentsEnvironment(x)(y)
  }

  override def setContent(x: Int, y: Int, agent: Agent): Unit ={
    agentsEnvironment(x)(y) = agent
  }

  override def deleteContent(x: Int, y: Int) = {
    agentsEnvironment(x)(y) = null;
  }

  def move(particle: Particle, x: Int, y: Int): Unit = {
    this.setContent(x, y, particle)
    this.deleteContent(x,y)
  }


}
