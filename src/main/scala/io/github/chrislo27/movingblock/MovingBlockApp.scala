package io.github.chrislo27.movingblock

import javafx.application.Application
import javafx.stage.{Screen, Stage}

import com.sun.javafx.font.PrismFontFactory

import scala.collection.JavaConverters._


class MovingBlockApp extends Application {

  private def handleHDPI(): Unit = {
    val fontSize = getParameters.getNamed.asScala.get("fontSize").map(_.toDouble)
    if (util.Properties.isLinux || fontSize.isDefined) {
      val systemFontSize = Screen.getPrimary.getDpi match {
        case -1 => fontSize
        case screenDpi => Some(screenDpi / 6) // 12 points
      }

      systemFontSize foreach { systemFontSize =>
        val clazz = classOf[PrismFontFactory].getDeclaredField("systemFontSize")
        clazz.setAccessible(true)
        clazz.setFloat(null, systemFontSize.toFloat)
      }
    }
  }

  override def init(): Unit = {
    handleHDPI()
  }

  override def start(primaryStage: Stage) {
    primaryStage.show()
  }

}

object MovingBlockApp {

  def main(args: Array[String]): Unit = Application.launch(classOf[MovingBlockApp])

}
