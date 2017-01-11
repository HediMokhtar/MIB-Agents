package fil.iagl.idl.sma.particles.model

import javafx.scene.paint.Color
import fil.iagl.idl.sma.core.model.Agent
import scala.util.Random

/**
  * Little colored and cute particle
  */

case class Particle (var x: Int,
                     var y: Int) extends Agent {

  val color: Color = assignRandomColor()
  var direction: (Int, Int) = assignRandomDirection()

  def assignRandomColor(): Color ={
    val randomPrimaryColor = new Random()
    val opacity = 1
    new Color(randomPrimaryColor.nextDouble(),randomPrimaryColor nextDouble(), randomPrimaryColor.nextDouble(),opacity)
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

  def getNextCoordinates(environment: ParticleEnvironment ): (Int,Int) = {
    var xDirection = direction._1
    var yDirection = direction._2

    if (environment.toric) {
      val newX = if ((x + xDirection) >= 0) x + xDirection else (x + xDirection) + environment.width
      val newY = if ((y + yDirection) >= 0) y + yDirection else (y + yDirection) + environment.height
      (newX % environment.width, newY % environment.height)
    }
    else {
      var newX = x + xDirection
      var newY = y + yDirection

      if ((newX < 0) || (newX >= environment.width)) {
        xDirection = -xDirection
        newX = x + xDirection
      }

      if ((newY < 0) || (newY >= environment.height)) {
        yDirection = -yDirection
        newY = y + yDirection
      }
      (newX, newY)
    }
  }

}
