'use strict';

module.exports = function MessagesService($state, ChatService) {
  var chat = ChatService.chat();

  chat.on('end', () => {
    console.log("End of chat");
    $state.go('logout');
  });

  chat.on('error', (e) => {
    console.error("Error", e);
    $state.go('logout');
  });

  return chat;
};
