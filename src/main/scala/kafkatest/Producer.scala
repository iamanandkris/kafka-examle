package kafkatest
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

class Producer {

  val producer = new KafkaProducer[String, String](configuration)

  private def configuration: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    props
  }

  def sendMessages(partition:Int, key:String, message:String): Unit = {
    val record = new ProducerRecord[String, String]("testtopic", partition, key, message)
    producer.send(record)
  }

  def closeProducer : Unit = producer.close()
}
