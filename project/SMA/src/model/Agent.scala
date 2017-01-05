package model

import com.sun.prism.paint.Color

/**
  * Trait is an abstract class like in Java
  * We imagine that there will many kind of Agents on that project
  */

trait Agent{

  def decide(): Unit

  def update(): Unit

}
