# Scala gRPC

POC of gRPC with Scala language

## Requirements

 * [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 * [sbt 0.13.13](http://www.scala-sbt.org/download.html)

## Usage

### Generate code from protobuf files

run the following commands

```
sbt
project domain
compile
```

### Run Server

run the following commands

```
sbt
project chat-grpc-server
run
```

## Reason

Have a closer look to gRPC:

 * Validate ease of use
 * Validate cross languages code generation
 * Implement bidirectional streaming

## TODO

 * [ ] add support for image message type
 * [ ] add support for WhatsApp message types
