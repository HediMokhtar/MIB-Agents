package model

/**
  * Represents the playground of multi agent environment
  */

class Environment (val length: Int, val width: Int, val isToric: Boolean) {

  val agentsEnvironment = Array.ofDim[Agent](length,width)


}
