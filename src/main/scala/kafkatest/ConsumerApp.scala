package kafkatest

object ConsumerApp extends App{
  val consumer = new Consumer
  consumer.receiveMessages()
}
