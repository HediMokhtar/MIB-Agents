package fil.iagl.idl.sma

import fil.iagl.idl.sma.particles.util.ParticlesCommandOptions
import fil.iagl.idl.sma.particles.view.ParticlesView
import fil.iagl.idl.sma.wator.util.WatorCommandOptions
import fil.iagl.idl.sma.wator.view.WatorView
import org.backuity.clist.Cli

object Main {

  def main (args: Array[String]): Unit = {
    Cli.parse(args).withProgramName("sma").withCommands(ParticlesCommandOptions, WatorCommandOptions) match {
      case Some(ParticlesCommandOptions) => ParticlesView.main(args)
      case Some(WatorCommandOptions) => WatorView.main(args)
      case _ => ()
    }
  }

}
