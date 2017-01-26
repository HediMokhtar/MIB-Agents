package fil.iagl.idl.sma.core.util

object AgentType {

  sealed trait Type
  case object FISH extends Type
  case object SHARK extends Type
  case object NO_TYPE extends Type
  case object OBSTACLE extends Type
  case object AVATAR extends Type
  case object HUNTER extends Type

}
