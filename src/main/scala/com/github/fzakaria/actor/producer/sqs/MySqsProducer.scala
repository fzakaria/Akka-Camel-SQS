package com.github.fzakaria.actor.producer.sqs

import akka.camel.{ CamelMessage, Producer }
import akka.actor.Actor

//For sending messages to Camel endpoints, actors need to mixin the Producer trait 
//and implement the endpointUri method.
//This means that sending a message to this actor will route it to SQS!
//The Producer always sends messages asynchronously. Response messages (if supported by the configured endpoint) will, 
//by default, be returned to the original sender however for SQS this won't work
class MySqsProducer extends Actor with Producer {
   override def endpointUri = "aws-sqs://sqs-akka-camel?amazonSQSClient=#client"
   override def oneway: Boolean = true
}