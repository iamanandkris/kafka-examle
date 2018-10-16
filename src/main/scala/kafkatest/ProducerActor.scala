package kafkatest

import java.util.Random

import akka.actor.Actor
import kafkatest.ProducerActor.StartProducing

object ProducerActor{
  case object StartProducing
}
class ProducerActor extends Actor{

  val producer = new Producer()
  var counter = 0
  var actualCounter = 0

  override def receive: Receive = {
    case StartProducing =>
      println(s"Received produce request, producing - ${actualCounter}")
      val i = producer.sendMessages(counter.toString, s"${actualCounter}")
      println(s"produced - ${i.partition()}/${i.offset()}")
      actualCounter = actualCounter + 1
      counter = (actualCounter+ 1)%15
  }
}
