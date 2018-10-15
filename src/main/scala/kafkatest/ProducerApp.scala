package kafkatest

import akka.actor.{ActorSystem, Props}
import kafkatest.ProducerActor.StartProducing

import scala.concurrent.duration._

object ProducerApp extends App{

  val actorSystem = ActorSystem("testActorSystem")
  val tickActor = actorSystem.actorOf(Props(classOf[ProducerActor]))

  import actorSystem.dispatcher

  val cancellable =
    actorSystem.scheduler.schedule(
      0 milliseconds,
      50 milliseconds,
      tickActor,
      StartProducing)

}
