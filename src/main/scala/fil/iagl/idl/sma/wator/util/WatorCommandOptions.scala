package fil.iagl.idl.sma.wator.util

import org.backuity.clist._


object WatorCommandOptions extends Command(name = "wator") {

  var width = opt[Int](name = "width", default = 150)
  var height = opt[Int](name = "height", default = 150)
  var nbFishes = opt[Int](name = "nFishes", default = 600)
  var nbSharks = opt[Int](name = "nSharks", default = 200)
  var fishBreedTime = opt[Int](name = "tBreed", default = 2)
  var sharkBreedTime = opt[Int](name = "sBreed", default = 9)
  var sharkStarveTime = opt[Int](name = "starve", default = 3)
  var speed = opt[Int](name = "speed", default = 75)

}
