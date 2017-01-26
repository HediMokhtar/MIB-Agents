package fil.iagl.idl.sma.core.util

import javafx.scene.shape.Shape

import fil.iagl.idl.sma.core.model.Agent

import scala.collection.mutable

trait Observer {

  def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit

}