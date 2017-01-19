package fil.iagl.idl.sma

import fil.iagl.idl.sma.particles.model.{Particle, ParticleEnvironment}
import fil.iagl.idl.sma.particles.view.ParticlesView

object Main extends App{
    var x = 1
    val particleEnvironment = new ParticleEnvironment(10000,10000,10000,10,false)
   // println("les agents en jeu : " + particleEnvironment.agents.toString())
    particleEnvironment.init()
   // particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }
    particleEnvironment.nextState()
        // particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }

    /**
    val argues: Array[String] = new Array[String](5)
    argues(0) = "0"
    argues(1) = "0"
    argues(2) = "10"
    argues(3) = "5"
    argues(4) = "true"

    ParticlesView.main(args)

  **/
}
