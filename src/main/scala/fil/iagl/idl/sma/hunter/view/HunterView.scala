package fil.iagl.idl.sma.hunter.view

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.sma.core.util.Direction
import fil.iagl.idl.sma.hunter.model.HunterModel
import fil.iagl.idl.sma.hunter.util.HunterCommandOptions

class HunterView extends Application {

  val primScreenBounds = Screen.getPrimary.getVisualBounds
  if ((HunterCommandOptions.envWidth, HunterCommandOptions.envHeight) ==(0, 0)) {
    HunterCommandOptions.envWidth = primScreenBounds.getWidth.toInt
    HunterCommandOptions.envHeight = primScreenBounds.getHeight.toInt
  }
  val canvas = new Pane()
  val scene = new Scene(canvas, HunterCommandOptions.envWidth * 5, HunterCommandOptions.envHeight * 5)

  override def start(primaryStage: Stage): Unit = {
    val model = new HunterModel(HunterCommandOptions.nbHunters, HunterCommandOptions.nbObstacles, HunterCommandOptions.envHeight, HunterCommandOptions.envWidth, HunterCommandOptions.agentSize)
    model.init()
    primaryStage.setTitle("Hunter")
    primaryStage.setScene(scene)
    primaryStage.show()

    model.agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))

    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler[KeyEvent] {
      override def handle(event: KeyEvent): Unit = {
        event.getCode match {
          case KeyCode.UP =>  model.avatar.direction = Direction.NORTH
          case KeyCode.DOWN => model.avatar.direction = Direction.SOUTH
          case KeyCode.RIGHT => model.avatar.direction = Direction.EAST
          case KeyCode.LEFT => model.avatar.direction = Direction.WEST
          case _ => ()

        }
      }
    })
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(HunterCommandOptions.speed), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        model.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play()
  }

}

object HunterView {

  def main(args: Array[String]) {
    Application.launch(classOf[HunterView], args: _*)
  }

}
