package fil.iagl.idl.sma.core.util

import javafx.scene.shape.Shape

import fil.iagl.idl.sma.particles.model.Agent
import fil.iagl.idl.sma.particles.util.ParticlesRandomColorGenerator

import scala.collection.mutable

class AgentsShapes {

  val agentsToShapesAssociations = mutable.Map[Agent, Shape]()

  def linkAgentToShape(agent: Agent, shape: Shape): Unit = agentsToShapesAssociations += (agent -> shape)

  def relocateShape(agent: Agent, x: Int, y: Int): Unit = agentsToShapesAssociations(agent).relocate(x, y)

  def recolor(agent: Agent): Unit = {
    agentsToShapesAssociations(agent).setFill(ParticlesRandomColorGenerator.randomColor())
  }

}

object AgentsShapes {

  def apply(): AgentsShapes = new AgentsShapes

}
