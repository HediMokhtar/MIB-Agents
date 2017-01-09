package fil.iagl.idl.sma


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, StackPane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text

object FXTest extends JFXApp {
  stage = new PrimaryStage {
    title = "ScalaFX Hello World"
    scene = new Scene {
      fill = Black
      content = new HBox {

        padding = Insets(20)
        children = Seq(
          new Text {
            text = "Multi Agent "
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          },
          new Text {
            text = "System"
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(Cyan, DodgerBlue)
            )
            effect = new DropShadow {
              color = DodgerBlue
              radius = 25
              spread = 0.25
            }
          }
        )
      }
    }
  }

  /**
  object SimpleScalaFXApp extends JFXApp {
    stage = new PrimaryStage {
      title = "Simple ScalaFX App"
      scene = new Scene {
        root = new StackPane {
          padding = Insets(20)
            content = new Rectangle {
            width = 200
            height = 200
            fill = Color.DEEPSKYBLUE0
          }
        }
      }
    }
  }**/
}