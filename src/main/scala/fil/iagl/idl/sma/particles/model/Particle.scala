package fil.iagl.idl.sma.particles.model

import scalafx.scene.paint.Color

import fil.iagl.idl.sma.core.model.{Agent, Direction}

import scala.util.Random

/**
  * Little colored and cute particle
  */

class Particle (var x: Int,
                var y: Int,
                val environment: ParticleEnvironment) extends Agent {

  val color: Color = assignRandomColor()

  def assignRandomColor(): Color ={
    val randomPrimaryColor = new Random()
    Color.rgb(randomPrimaryColor.nextInt(255),randomPrimaryColor.nextInt(255), randomPrimaryColor.nextInt(255))
  }

  def changeDirection(): (Int,Int) ={
    val newDirection = assignRandomDirection()
    if(newDirection != direction)
      newDirection
    else
      changeDirection()
  }

  override def doIt(): Unit = {
    val next = this.getNextCoordinates
    if(environment.isAvailable(next._1,next._2)) {
      environment.move(next._1, next._2, this)
      environment.view.agentsAndViewGateway(this).centerX = next._1
      environment.view.agentsAndViewGateway(this).centerX = next._2
    }
    else{
      this.changeDirection()
    }

  }

  /**
    * Generate new random coordinates depending of the environment width and height
    */
  def rerollCoordinates(maxWidth: Int, maxHeight: Int): Unit={
    x = Random.nextInt(maxHeight)
    y = Random.nextInt(maxWidth)
  }


  override def getNextCoordinates: (Int,Int) = {
    if (environment.isToric) {
      val newX = getToricNextX
      val newY = getToricNextY
      (newX % environment.width, newY % environment.height)
    }
    else {
      var newX = x + xDirection
      var newY = y + yDirection

      if (isOutOfWidthBounds(newX)) {
        xDirection = -xDirection
        newX = x + xDirection
      }

      if (isOutOfHeightBounds(newY)) {
        yDirection = -yDirection
        newY = y + yDirection
      }
      (newX, newY)
    }
  }


}
