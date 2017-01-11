package fil.iagl.idl.sma.particles.util

import org.backuity.clist._

object ParticlesCommandOptions extends Command(name = "particles") {

  var nbParticles = opt[Int](name = "nbParticles", default = 300)
  var envWidth = opt[Int](name = "envWidth", default = 300)
  var envHeight = opt[Int](name = "envHeight", default = 300)
  var agentSize = opt[Double](name = "agentSize", default = 10)
  var speed = opt[Int](name = "speed", default = 5)
  var toroidal = opt[Boolean]( default = false, name = "toroidal")
  var visible = opt[Boolean](name = "visible")
  var equity = opt[Int](name = "equity", default=0)
  var nbTicks = opt[Int](name = "nbTicks", default = 0)

}
