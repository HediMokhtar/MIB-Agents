package fil.iagl.idl.sma

import fil.iagl.idl.sma.particles.model.{Particle, ParticleEnvironment}
import fil.iagl.idl.sma.particles.view.ParticlesView

import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.geometry.Rectangle2D
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.stage.Screen

object Main extends JFXApp{
    /**
    var x = 1
    val particleEnvironment = new ParticleEnvironment(10000,10000,10000,10,false)
   // println("les agents en jeu : " + particleEnvironment.agents.toString())
    particleEnvironment.init()
   // particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }
    particleEnvironment.nextState()
        // particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }


    val argues: Array[String] = new Array[String](5)
    argues(0) = "0"
    argues(1) = "0"
    argues(2) = "10"
    argues(3) = "5"
    argues(4) = "true"

    ParticlesView.main(args)

  **/


    val bounds: Rectangle2D = Screen.primary.bounds
    val particleEnvironment = new ParticleEnvironment(501,501,100,3,true)

    stage = new JFXApp.PrimaryStage{
        title.value = "Particles"

        scene = new Scene(particleEnvironment.width,particleEnvironment.height){

            particleEnvironment.init()

            var circles: List[Circle] = particleEnvironment.view.getAllParticlesElementView(particleEnvironment.agents)
            content = circles


            var lastTime = 0L
            val speed = 20
            val timer = AnimationTimer(t => {
                if(lastTime>0){
                    particleEnvironment.nextState()
                }
                lastTime = t
            })
            timer.start
        }

/**

            var enemies = List(Circle(10,10,10))
            val player = Circle (300,300,20)
            player.fill = Color.Green
            content = List(enemies.head,player) **/
        }








}
