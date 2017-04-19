# Scala gRPC

POC of gRPC with Scala & NodeJS languages

## Requirements

 * [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 * [sbt 0.13.13](http://www.scala-sbt.org/download.html)

## Usage

### Generate code from protobuf files

Run the following commands:

```
sbt
project domain
compile
```

_(Running the client or the server will generate sources too)_

### Run Server

Run the following commands:

```
sbt
project chat-grpc-server
run
```

### Run Scala Client

Ensure server is running.
Then run the following commands:

```
sbt
project chat-grpc-client
run
```

you can run multiple Java clients simultaneously

### Run Electron Client

See dedicated README

## Reason

Have a closer look to gRPC:

 * Validate ease of use
 * Validate cross languages code generation
 * Implement bidirectional streaming

## TODO

 * [ ] add support for image message type
 * [ ] add support for WhatsApp message types
