/*jshint maxparams:6*/
/*global  _ */
'use strict';
var ChatMessage = require('../proto/chat_pb').ChatMessage;

module.exports = function MessagesCtrl($scope, $state, UserService, MessagesService) {

  this.messages = [];
  this.input = "";

  // TODO: abstract that part in a service
  MessagesService.on('data', function(message) {
    // triggers anew digest cycle
    $scope.$apply(this.messages.push(message));
  }.bind(this));

  /**
   * clean the input value
   */
  this.cleanInput = function() {
    this.input = "";
  };

  /**
   * add a new message to conversation
   */
  this.add = function () {
    if (this.input.trim() !== '') {
      var message = new ChatMessage([UserService.getUserId(), this.input, ChatMessage.ChatMessageType.CHAT]);
      MessagesService.write(message);
      this.cleanInput();
    }
  };

};
