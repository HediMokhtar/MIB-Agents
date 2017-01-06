package fil.iagl.idl.sma.core.model

class Environment (length: Int, width: Int, isToric: Boolean) {

  val agentsEnvironment = Array.ofDim[Agent](length,width)

}
