# gRPC web client

## cannot be run in a browser :(

 * [gRPC](https://www.npmjs.com/package/grpc) package is hardly browserifyable
 * [fetch](https://github.com/whatwg/fetch) is not implemented in all browsers yet

## Requirements

https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-WEB.md


## Usage

There are some issues with latest version of grpc-tools, so you may need to compile proto files to JS manually

install protobuf (`brew install protobuf` on OSX)
install `grpc-tools` (`npm i grpc-tools`)
copy grpc-tools/bin/grpc_node_plugin binary to your path (`/usr/local/bin` for example)

generate JS code from your proto files
protoc --proto_path=. --js_out=import_style=commonjs,binary:static/ --grpc_out=node/ --plugin=protoc-gen-grpc=/usr/local/bin/grpc_node_plugin chat.proto


## Static code generation VS dynamic code generation

### Dynamic code generation
 
 * no auto-completion
 * need hand-crafted JSON call objects
 * enums becomes obscure integers

### Static code generation

 * with latest version of grpc-tools, only models are generated using grpc_node_plugin
