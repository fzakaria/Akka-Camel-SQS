package com.github.fzakaria

import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import com.github.fzakaria.actor.consumer.sqs.MySqsConsumer
import com.github.fzakaria.actor.producer.sqs.MySqsProducer
import akka.camel.CamelExtension
import com.amazonaws.auth._
import com.amazonaws.services.sqs._
import org.apache.camel.impl.SimpleRegistry
import scala.concurrent.duration._

object Main extends App {

  // Create the actor system
  val system = ActorSystem("sqs-akka-camel")
  
  //One CamelExtension is only loaded once for every one ActorSystem, 
  //which makes it safe to call the CamelExtension at any point in 
  //your code to get to the Apache Camel objects associated with it.
  val camel = CamelExtension(system)
  
  val camelContext = camel.context
  
 /* Lets create our SQS Client
 * 1. Create our DefaultAWSCredentialsProviderChain provider chain
  AWS credentials provider chain that looks for credentials in this order:
  Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY
  Java System Properties - aws.accessKeyId and aws.secretKey
  Credential profiles file at the default location (~/.aws/credentials) shared by all AWS SDKs and the AWS CLI
  Instance profile credentials delivered through the Amazon EC2 metadata service
  */
  val providerChain = new DefaultAWSCredentialsProviderChain() 
  val sqsClient = new AmazonSQSClient(providerChain);
  
  //bind the client to the context
  //we can also just put the credentials in the URI however we might want to create
  //clients for other reasons anyways.
  //http://camel.apache.org/aws-sqs.html
  val registry = new SimpleRegistry()
  registry.put("client", sqsClient)
  camelContext.setRegistry(registry)
  
  
  //create the consumer actor!
  val consumer = system.actorOf(Props[MySqsConsumer], "consumer")
  
  //create the producing actor
  val producer = system.actorOf(Props[MySqsProducer], "producer")
  
  //lets schedule "Hello World!" every 3 seconds.
  //This should now go through SQS to the actor!
  
  //Use the system's dispatcher as ExecutionContext
  import system.dispatcher
  system.scheduler.schedule(0 seconds, 3 seconds, producer, "Hello World!")
  
}