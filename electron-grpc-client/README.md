# gRPC Electron client

Since gRPC cannot be use in a browser, Electron is the solution \o/

## Requirements

[Node v6](https://nodejs.org/en/download/)

## Usage

`npm i` (grpc package will be rebuilt for electron runtime)

Start the server (see chat-grpc-server README)

Then run `npm start`, that's it (you can chat with a java client, see chat-grpc-client)

## Regenerate JS code from .proto

There are some issues with latest version of grpc-tools, so you may need to compile proto files to JS manually

install protobuf (`brew install protobuf` on OSX)
install `grpc-tools` (`npm i grpc-tools`)
copy grpc-tools/bin/grpc_node_plugin binary to your path (`/usr/local/bin` for example)

generate JS code from the proto files
```
protoc --proto_path=. --js_out=import_style=commonjs,binary:static/ --grpc_out=node/ --plugin=protoc-gen-grpc=/usr/local/bin/grpc_node_plugin chat.proto
```
move generated files to `src/proto/`
