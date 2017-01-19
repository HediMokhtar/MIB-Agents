package fil.iagl.idl.sma.wator.model

import fil.iagl.idl.sma.core.model.{Agent, Environment}

import scala.util.Random

class WatorEnvironment (val height: Int,
                        val width : Int,
                        val sharksNumber: Int,
                        val fishesNumber: Int,
                        val agentsSize: Double,
                        val isToric: Boolean)extends Environment{

  override val agents: List[Fish] = List.fill(fishesNumber)(new Fish(Random.nextInt(height),Random.nextInt(width),this))


  override def init(): Unit = ???

  /**
    * Launch the environment
    */
  override def run(): Unit = ???
}
