package fil.iagl.idl.sma.particles.model

import java.awt.Color
import fil.iagl.idl.sma.core.model.Agent
import scala.util.Random

/**
  * Little colored and cute particle
  */

case class Particle (var x: Int,
                     var y: Int) extends Agent {

  val color: Color = assignRandomColor()
  var direction: (Int, Int) = assignRandomDirection()

  override def update(): Unit = ???

  override def decide(): Unit = ???

  def assignRandomColor(): Color ={
    val randomPrimaryColor = new Random()
    new Color(randomPrimaryColor.nextFloat(),randomPrimaryColor.nextFloat(),randomPrimaryColor.nextFloat())
  }

  def assignRandomDirection(): (Int,Int) ={
    val possibleDirections = Direction.DIRECTIONS
    val randomDirection = new Random()
    possibleDirections.toVector(randomDirection.nextInt(possibleDirections.size))
  }

  def setOppositeDirection(): Unit ={
    val oppositeX = direction._1 * -1
    val oppositeY = direction._2 * -1
    direction = (oppositeX,oppositeY)
  }

  /**
    * Generate new random coordinates depending of the environment width and height
    */
  def reroll(maxWidth: Int, maxHeight: Int): Unit={
    x = Random.nextInt(maxHeight)
    y = Random.nextInt(maxWidth)
  }

}
