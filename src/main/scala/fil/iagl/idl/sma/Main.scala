package fil.iagl.idl.sma

import fil.iagl.idl.sma.particles.model.{Particle, ParticleEnvironment}

object Main extends App{

    val particleEnvironment = new ParticleEnvironment(4,4,2,10,true)
    println(particleEnvironment.agents.toString())
    particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }
    particleEnvironment.init()
    println()
    particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }
    particleEnvironment.nextState()
    particleEnvironment.agentsEnvironment foreach { row => row foreach print; println }



}
