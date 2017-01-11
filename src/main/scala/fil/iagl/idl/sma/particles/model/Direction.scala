package fil.iagl.idl.sma.particles.model

/**
  * Correspond to the 8 directions possible
  */
  object Direction{

  val NORTH: (Int, Int) = (0,1)
  val EAST: (Int, Int) = (1,0)
  val WEST: (Int, Int) = (-1,0)
  val SOUTH: (Int, Int) = (0,-1)
  val NORTH_WEST: (Int, Int) = (-1,1)
  val NORTH_EAST: (Int, Int) = (1,1)
  val SOUTH_WEST: (Int, Int) = (-1,-1)
  val SOUTH_EAST: (Int, Int) = (1,-1)
  val DIRECTIONS = Set(NORTH,EAST,WEST,SOUTH,NORTH_WEST,NORTH_EAST,SOUTH_WEST,SOUTH_EAST)

  }

