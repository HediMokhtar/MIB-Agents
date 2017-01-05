package model

import com.sun.prism.paint.Color

/**
  * Created by franc on 04-Jan-17.
  */
trait Agent{

  def decide(): Unit

  def update(): Unit

}
