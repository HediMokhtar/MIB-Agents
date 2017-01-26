package fil.iagl.idl.sma.wator.model

import javafx.scene.paint.Color

import fil.iagl.idl.sma.core.model.Environment
import fil.iagl.idl.sma.core.util.{AgentType, AgentsShapes, Neighborhood}


class Fish(breed: Int, val environment: Environment) extends WatorAgent(breed) {

  agentType = AgentType.FISH

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    if(age == 1)
      agentsShapes.recolor(this, Color.BLUE)
    else
    if(age == 0)
      agentsShapes.recolor(this, Color.GREEN)
    val nextPotentialPosition = nextFreePosition(environment)
    nextPotentialPosition match {
      case Some(x) => {
        val potentialChild = Fish(breed, environment)
        moveAndAimToReproduce(environment, potentialChild, Color.GREEN, agentsShapes)
        position = nextPotentialPosition.get
        agentsShapes.relocateShape(this, position.x * 4, position.y * 4)
        environment.mark(position.x, position.y, this)
      }
      case None => {
        breedCounter += 1
        age += 1
      }
    }
  }
}

object Fish {

  def apply(breed: Int, environment: Environment) = new Fish(breed, environment)

}