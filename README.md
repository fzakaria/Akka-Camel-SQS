Akka Apache-Camel Via SQS
=========
This is an example project of how to setup a sample project using [Akka](http://doc.akka.io/) , [Apache-Camel](http://camel.apache.org/) and [SQS](http://aws.amazon.com/sqs/) together. Never heard of them or curious how they interact with each other?

####Apache-Camel
>Apache Camel is a rule-based routing and mediation engine.

What that basically means is that Apache Camel provides a common API for exchanging messages across a variety of platforms/protocols such as: HTTP, SQS, AMQP

####Akka
>Akka is a toolkit and runtime for building highly concurrent, distributed, and fault tolerant event-driven applications on the JVM.

What that basically means is that Akka is a framework for writing code (known as [actors](http://en.wikipedia.org/wiki/Actor_model)) that lend to code to be easily distributed.

####SQS
>Amazon Simple Queue Service (SQS) is a fast, reliable, scalable & affordable message queuing service.

###Why should you care!?
I've recently had the pleasure of releasing some code on [heroku](https://heroku.com) using the [PlayFramework](https://www.playframework.com/). Although deployment and initial setup was a breeze, I was bummed out to find that using Akka's protocol was not doable as only standard ports on heroku are allowed (80,443). This leads to being unable to use Akka actors in a proper distributed model (i.e. they can't talk to each other!)
>Heroku has made a [post](https://devcenter.heroku.com/articles/scaling-out-with-scala-and-akka) where they outline using RabbitMq instead of the default Akka protocol however I did not find simple / ideal.

This brings us to this sample project! Leveraging Apache-Camel & SQS it was very straightforward to send messages to distributed actors.

###How to run
1. Make sure your AWS credentials are somewhere that be found by the AwsCredentialsProvider
  * Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY
  * Java System Properties - aws.accessKeyId and aws.secretKey
  * Credential profiles file at the default location (~/.aws/credentials) shared by all AWS SDKs and the AWS CLI
  * Instance profile credentials delivered through the Amazon EC2 metadata service
2. Launch sbt
3. Run command `run`
