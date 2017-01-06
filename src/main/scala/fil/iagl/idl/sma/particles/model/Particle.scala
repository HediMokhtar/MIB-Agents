package fil.iagl.idl.sma.particles.model

import java.awt.Color

import fil.iagl.idl.sma.core.model.Agent

class Particle (val color: Color, var x: Int, var y: Int) extends Agent {

  override def update(): Unit = ???

  override def decide(): Unit = ???

}
