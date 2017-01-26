package fil.iagl.idl.sma.core.model

import fil.iagl.idl.sma.core.util.AgentsShapes

import scala.collection.mutable


trait Model {

  var agents = mutable.HashSet[Agent]()

  val agentsShapes = AgentsShapes()

  def init(): Unit

  def run(): Unit

}
