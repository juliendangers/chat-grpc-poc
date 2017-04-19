'use strict';

var angular = require('angular');

module.exports = function(bindonce, users, chatService) {
  return angular.module('messages', [
    'pasvaz.bindonce',
    users.name
  ])
    .directive("scrollBottom", require('./ScrollBottomDirective'))
    .factory('ChatService', chatService)
    .factory('MessagesService', ['$state', 'ChatService', require('./MessagesService')])
    .controller('MessagesCtrl', ['$scope', '$state', 'UserService', 'MessagesService', require('./MessagesCtrl')]);
};
