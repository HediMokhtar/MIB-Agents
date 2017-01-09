package fil.iagl.idl.sma.particles.model

import java.awt.Color
import fil.iagl.idl.sma.core.model.Agent
import scala.util.Random

/**
  * Little colored and cute particle
  */

class Particle (val color: Color,
                var x: Int,
                var y: Int,
                var direction: Int
               ) extends Agent {

  override def update(): Unit = ???

  override def decide(): Unit = ???

  def createRandomColor(): Color ={
    val randomPrimaryColor = new Random()
    new Color(randomPrimaryColor.nextFloat(),randomPrimaryColor.nextFloat(),randomPrimaryColor.nextFloat())
  }


}
