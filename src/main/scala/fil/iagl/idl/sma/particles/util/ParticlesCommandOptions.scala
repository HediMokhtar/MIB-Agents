package fil.iagl.idl.sma.particles.util

import org.backuity.clist._

object ParticlesCommandOptions extends Command(name = "particles") {

  var nbParticles = opt[Int](name = "nbParticles", default = 100)
  var envWidth = opt[Int](name = "envWidth", default = 200)
  var envHeight = opt[Int](name = "envHeight", default = 100)
  var agentSize = opt[Double](name = "agentSize", default = 5)
  var speed = opt[Int](name = "speed", default = 30)
  var toroidal = opt[Boolean](name = "toroidal")
  var visible = opt[Boolean](name = "visible")
  var equity = opt[Boolean](name = "equity")

}
