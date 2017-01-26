package fil.iagl.idl.sma.hunter.model

import fil.iagl.idl.sma.core.model.{Agent, Environment}
import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Neighborhood}

class Obstacle(environment: Environment) extends Agent{
  agentType = AgentType.OBSTACLE

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {}
}

object Obstacle {

  def apply(environment: Environment) = new Obstacle(environment)

}

