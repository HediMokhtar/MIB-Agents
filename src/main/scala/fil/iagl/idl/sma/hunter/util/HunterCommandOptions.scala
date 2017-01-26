package fil.iagl.idl.sma.hunter.util

import org.backuity.clist._

object HunterCommandOptions extends Command(name="hunter") {

  var nbHunters = opt[Int](name = "nbHunters", default = 2)
  var nbObstacles = opt[Int](name = "nbObstacles", default = 50)
  var envWidth = opt[Int](name = "envWidth", default = 150)
  var envHeight = opt[Int](name = "envHeight", default = 150)
  var agentSize = opt[Double](name = "agentSize", default = 5)
  var speed = opt[Int](name = "speed", default = 70)
  var logger = opt[Boolean](name = "logger", default = true)

}
