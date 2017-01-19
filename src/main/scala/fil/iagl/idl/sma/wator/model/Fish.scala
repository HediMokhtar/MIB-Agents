package fil.iagl.idl.sma.wator.model

import scalafx.scene.paint.Color

import fil.iagl.idl.sma.core.model.{Agent, Environment}

class Fish(var x: Int,
           var y: Int,
           val environment: WatorEnvironment) extends Agent{

  override val color: Color = Color.Aqua

  override def doIt(): Unit = {


  }

  override def getNextCoordinates: (Int, Int) = {
    direction = assignRandomDirection()
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
