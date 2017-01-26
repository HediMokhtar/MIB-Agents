package fil.iagl.idl.sma.wator.util

object WatorMetricsData {

  var nFishes = 0

  var nSharks = 0

  def incrementNFishes(): Unit = nFishes += 1

  def incrementNSharks(): Unit = nSharks += 1

  def decrementNFishes(): Unit = nFishes -= 1

  def decrementNSharks(): Unit = nSharks -= 1

}
