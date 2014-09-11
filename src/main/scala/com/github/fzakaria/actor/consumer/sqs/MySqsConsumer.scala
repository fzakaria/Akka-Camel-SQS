package com.github.fzakaria.actor.consumer.sqs

import akka.camel.{ CamelMessage, Consumer }

class MySqsConsumer extends Consumer {

  //The SQS URI is an in-only message exchange (autoAck=true)
  override def endpointUri = "aws-sqs://sqs-akka-camel?amazonSQSClient=#client"
 
  override def receive = {
    case msg: CamelMessage => {
      println("received %s" format msg.bodyAs[String])
    }
    
  }
  
}