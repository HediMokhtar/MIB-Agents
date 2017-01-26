package fil.iagl.idl.sma.core.model

import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Neighborhood, Position}

trait Agent {

  var position = Position(0, 0)

  val choices = List(-1, 0, 1)

  var agentType: AgentType.Type = AgentType.NO_TYPE

  var isVisited = false

  def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit

}
