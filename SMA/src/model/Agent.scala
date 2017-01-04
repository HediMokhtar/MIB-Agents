package model

import com.sun.prism.paint.Color

/**
  * Created by franc on 04-Jan-17.
  */
class Agent (var color: Color, var x: Int, var y: Int){

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  def decide(): Unit = {
    println("decide")
  }

  def update(): Unit = {
    println("update")
  }

}
