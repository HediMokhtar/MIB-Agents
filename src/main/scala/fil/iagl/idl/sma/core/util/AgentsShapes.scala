package fil.iagl.idl.sma.core.util

import javafx.scene.paint.Color
import javafx.scene.shape.Shape

import fil.iagl.idl.sma.core.model.Agent

import scala.collection.mutable

class AgentsShapes {

  val agentsToShapesAssociations = mutable.Map[Agent, Shape]()

  val shapesToDelete = mutable.HashSet [Shape]()

  def linkAgentToShape(agent: Agent, shape: Shape): Unit = agentsToShapesAssociations += (agent -> shape)

  def relocateShape(agent: Agent, x: Int, y: Int): Unit = agentsToShapesAssociations(agent).relocate(x, y)

  def removeAgent(agent: Agent): Unit = {
    shapesToDelete += agentsToShapesAssociations.get(agent).get
    agentsToShapesAssociations -= agent
  }

  def deleteShapesToDelete(): Unit = shapesToDelete.clear

  def recolor(agent: Agent, color: Color): Unit = {
      agentsToShapesAssociations(agent).setFill(color)
  }

}

object AgentsShapes {

  def apply(): AgentsShapes = new AgentsShapes

}
