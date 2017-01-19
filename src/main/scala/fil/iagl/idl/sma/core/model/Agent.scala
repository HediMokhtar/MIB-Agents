package fil.iagl.idl.sma.core.model

import scalafx.scene.paint.Color

import scala.util.Random

trait Agent{

  var x : Int
  var y : Int
  val environment : Environment
  val color : Color

  var direction: (Int, Int) = assignRandomDirection()
  var xDirection: Int = direction._1
  var yDirection: Int = direction._2

  def doIt() : Unit

  def setCoordinates(newX: Int, newY: Int): Unit ={
    x = newX
    y = newY
  }

  def getNextCoordinates: (Int,Int)

  def assignRandomDirection(): (Int,Int) ={
    val possibleDirections = Direction.DIRECTIONS
    val randomDirection = new Random()
    possibleDirections.toVector(randomDirection.nextInt(possibleDirections.size))
  }

  def getToricNextX: Int ={
    if ((x + xDirection) >= 0)
      x + xDirection
    else
      (x + xDirection) + environment.width
  }

  def getToricNextY: Int={
    if ((y + yDirection) >= 0)
      y + yDirection
    else
      (y + yDirection) + environment.height
  }

  def isOutOfWidthBounds(x: Int): Boolean={(x < 0) || (x >= environment.width)}
  def isOutOfHeightBounds(y: Int): Boolean={(y < 0) || (y >= environment.height)}

  /**
    * Return a free moore neighbours
    */
  def getAvailableMooreNeighboors(directions:List[(Int,Int)]): (Int,Int) ={
    direction = directions.head
    val newX = this.x + xDirection
    val newY = this.y + yDirection
    val newToricX = getToricNextX
    val newToricY = getToricNextY

    if(directions.isEmpty){
      (this.x,this.y)
    }

    else if(!environment.isToric && (isOutOfWidthBounds(newX) || isOutOfHeightBounds(newY))){
      getAvailableMooreNeighboors(directions.tail)
    }

    else if(!environment.isToric && environment.isAvailable(newX, newY)){
      (newX, newY)
    }

    else if(environment.isToric && environment.isAvailable(newToricX % environment.width, newToricY % environment.height)){
      (newToricX % environment.width, newToricY % environment.height)
    }
    else{
      getAvailableMooreNeighboors(directions.tail)
    }
  }

}
