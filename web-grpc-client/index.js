var PROTO_PATH = __dirname + '/../domain/src/main/proto/chat.proto';

var grpc = require('grpc');
var chatservice = grpc.load(PROTO_PATH).chat;

var client = new chatservice.ChatService('localhost:9000',
                                       grpc.credentials.createInsecure());
var chat;
try {
  chat = client.chat();
  chat.on('data', function (message) {
    console.log('Got message "' + message.content + '" from ' + message.name);
  });

  chat.on('end', () => {
    console.log("End of chat");
  });

  chat.on('error', (e) => {
    console.log("Error", e);
  });

  var msg = {name: 'test', content: 'mouhahah', type: 0};
  chat.write(msg);
} catch (e) {
  chat.end();
}