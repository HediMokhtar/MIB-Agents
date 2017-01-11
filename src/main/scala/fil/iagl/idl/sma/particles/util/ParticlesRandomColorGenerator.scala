package fil.iagl.idl.sma.particles.util

import javafx.scene.paint.Color

import scala.util.Random

object ParticlesRandomColorGenerator {
  val randomizer = new Random()

  def randomColor(): Color = {
    val randomRed = randomizer.nextDouble()
    val randomGreen = randomizer.nextDouble()
    val randomBlue = randomizer.nextDouble()
    val opacity = 1
    new Color(randomRed, randomGreen, randomBlue, opacity)
  }
}
