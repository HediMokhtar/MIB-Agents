package fil.iagl.idl.sma.particles.view

import javafx.scene.layout.Pane
import javafx.application.Application
import javafx.stage.{Screen, Stage}

import fil.iagl.idl.sma.particles.model.ParticleEnvironment

class ParticlesView extends Application{

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {

    val primaryScreenBounds = Screen.getPrimary.getBounds

    // Setting dimensions
    val width = primaryScreenBounds.getWidth.toInt
    val height = primaryScreenBounds.getHeight.toInt

    val particleEnvironment = new ParticleEnvironment(width, height,100,10,true)



  }
}

object ParticlesView{



}