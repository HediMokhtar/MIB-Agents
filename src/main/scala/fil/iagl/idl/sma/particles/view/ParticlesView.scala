package fil.iagl.idl.sma.particles.view

import fil.iagl.idl.sma.core.model.Agent
import fil.iagl.idl.sma.core.view.AgentsView
import fil.iagl.idl.sma.particles.model.{Particle, ParticleEnvironment}

import scala.collection.mutable
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Rectangle2D}
import scalafx.scene.control.Label
import scalafx.scene.layout.{BorderPane, Pane}
import scalafx.scene.shape.{Circle, Shape}
import scalafx.stage.{Screen, Stage}

class ParticlesView(val environment: ParticleEnvironment) extends AgentsView{

  val agentsAndViewGateway:mutable.Map[Particle, Circle] = mutable.Map[Particle, Circle]()
  /**
    * Make an Agent real in the view
    */
  def agentsMaterialization(particle: Particle, circle : Circle): agentsAndViewGateway.type = agentsAndViewGateway += (particle -> circle)


  def getAllParticlesElementView(particles : List[Particle]): List[Circle] ={
  particles.map(getShapeFromAgent)
  }

  def getShapeFromAgent(particle: Particle): Circle ={
    this.agentsAndViewGateway(particle)
  }

  /**
  val canvas = new Pane()

  def start(primaryStage: Stage): Unit = {



      // Setting dimensions



        * val particleEnvironment = new ParticleEnvironment(width, height,agentsNumber,agentsSize,toric)
        *particleEnvironment.init()
        *primaryStage.setTitle("Particles Party !")
        * val scene = new Scene(canvas, width*5, height*5)
        *primaryStage.setScene(scene)
        *primaryStage.show()
        **
        *val timelineLoop = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler[ActionEvent]() {
        * def handle(actionEvent: ActionEvent): Unit = {
        *particleEnvironment.run()
        * }
        * }))
        *timelineLoop.setCycleCount(-1)
        *timelineLoop.play() **/
    }

