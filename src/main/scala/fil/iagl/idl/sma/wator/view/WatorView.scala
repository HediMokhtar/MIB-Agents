package fil.iagl.idl.sma.wator.view

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{Orientation, Pos}
import javafx.scene.Scene
import javafx.scene.chart._
import javafx.scene.control.SplitPane
import javafx.scene.layout._
import javafx.scene.shape.Shape
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.sma.core.model.Agent
import fil.iagl.idl.sma.core.util.Observer
import fil.iagl.idl.sma.wator.model.WatorModel
import fil.iagl.idl.sma.wator.util.{WatorCommandOptions, WatorMetricsData}

import scala.collection.mutable

class WatorView extends Application with Observer {

  val canvas = new Pane()
  val primScreenBounds = Screen.getPrimary.getVisualBounds
  if ((WatorCommandOptions.width, WatorCommandOptions.height) == (0, 0)) {
    WatorCommandOptions.width = primScreenBounds.getWidth.toInt
    WatorCommandOptions.height = primScreenBounds.getHeight.toInt
  }
  val model = new WatorModel(WatorCommandOptions.width, WatorCommandOptions.height, WatorCommandOptions.nbFishes, WatorCommandOptions.nbSharks, WatorCommandOptions.fishBreedTime, WatorCommandOptions.sharkBreedTime, WatorCommandOptions.sharkStarveTime)
  model.init()
  model.addObserver(this)
  val fishesSeriesLineChart = new XYChart.Series[Number, Number]()
  val sharksSeriesLineChart = new XYChart.Series[Number, Number]()

  WatorMetricsData.nFishes = WatorCommandOptions.nbFishes
  WatorMetricsData.nSharks = WatorCommandOptions.nbSharks

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Wator")
    val root = new SplitPane
    val chartsContainer = new SplitPane
    chartsContainer.setOrientation(Orientation.VERTICAL)
    val xAxisLineChart = new NumberAxis
    val yAxisLineChart = new NumberAxis
    xAxisLineChart.setLabel("Elapsed time")
    yAxisLineChart.setLabel("Number of fishes")
    val lineChart = new LineChart[Number, Number](xAxisLineChart, yAxisLineChart)
    val xAxisBarChart = new CategoryAxis
    val yAxisBarChart = new NumberAxis
    xAxisBarChart.setLabel("Age")
    yAxisBarChart.setLabel("Number of fishes")

    val box = new VBox
    box.setAlignment(Pos.CENTER)
    val canvasContainer = new HBox
    canvasContainer.getChildren.add(canvas)
    canvasContainer.setAlignment(Pos.CENTER)
    box.getChildren.add(canvasContainer)
    chartsContainer.getItems.addAll(lineChart)
    root.getItems.addAll(box, chartsContainer)
    //root.getItems.addAll(box)
    lineChart.setTitle("Time-dependent number of fishes")
    lineChart.setCreateSymbols(false)
    fishesSeriesLineChart.setName("Fishes")
    fishesSeriesLineChart.getData.add(new XYChart.Data[Number, Number](0, WatorCommandOptions.nbFishes))
    sharksSeriesLineChart.setName("Sharks")
    sharksSeriesLineChart.getData.add(new XYChart.Data[Number, Number](0, WatorCommandOptions.nbSharks))
    lineChart.getData.addAll(fishesSeriesLineChart, sharksSeriesLineChart)
    val scene = new Scene(root, primScreenBounds.getWidth - 30, primScreenBounds.getHeight - 30)
    primaryStage.setScene(scene)
    primaryStage.show()
    val agentsShapes = model.agentsShapes
    agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(WatorCommandOptions.speed), new EventHandler[ActionEvent]() {
      override def handle(actionEvent: ActionEvent): Unit = {
        model.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play()
    var elapsedSeconds = 1
    val updateChartLoop = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler[ActionEvent]() {
      override def handle(actionEvent: ActionEvent): Unit = {
        fishesSeriesLineChart.getData.add(new XYChart.Data[Number, Number](elapsedSeconds, WatorMetricsData.nFishes))
        sharksSeriesLineChart.getData.add(new XYChart.Data[Number, Number](elapsedSeconds, WatorMetricsData.nSharks))
        elapsedSeconds += 1

      }
    }))
    updateChartLoop.setCycleCount(-1)
    updateChartLoop.play()

  }

  override def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit = {
    newAgents.foreach(newAgent => canvas.getChildren.add(model.agentsShapes.agentsToShapesAssociations.get(newAgent).get))
    deletedShapes.foreach(shape => canvas.getChildren.remove(shape))
  }
}

object WatorView {

  def main(args: Array[String]) {
    Application.launch(classOf[WatorView], args: _*)
  }

}
