package fil.iagl.idl.sma.particles.view

import javafx.animation.{KeyFrame, Timeline}
import javafx.scene.layout.Pane
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.sma.particles.model.ParticleEnvironment

class ParticlesView(var width: Int,
                    var height: Int,
                    val agentsNumber: Int,
                    val agentsSize: Int,
                    val toric: Boolean) extends Application{

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {

    val primaryScreenBounds = Screen.getPrimary.getBounds

    // Setting dimensions
    if(width <= 0) {
      width = primaryScreenBounds.getWidth.toInt
    }
    if(height <= 0) {
      height = primaryScreenBounds.getHeight.toInt
    }

    val particleEnvironment = new ParticleEnvironment(width, height,agentsNumber,agentsSize,toric)
    particleEnvironment.init()
    primaryStage.setTitle("Particles Party !")
    val scene = new Scene(canvas, width*5, height*5)
    primaryStage.setScene(scene)
    primaryStage.show()
    /**
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        particleEnvironment.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play() **/
  }
}