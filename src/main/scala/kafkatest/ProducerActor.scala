package kafkatest

import java.util.Random

import akka.actor.Actor
import kafkatest.ProducerActor.StartProducing

object ProducerActor{
  case object StartProducing
}
class ProducerActor extends Actor{

  val producer = new Producer()
  var counter = 1
  val randomval = scala.util.Random

  override def receive: Receive = {
    case StartProducing =>
      val value = randomval.nextString(5)
      println(s"Received produce request, producing - ${value}--${counter} to partition - ${counter}")
      producer.sendMessages(counter, value, s"${value}--${counter}")
      counter = (counter+ 1)%4
  }
}
