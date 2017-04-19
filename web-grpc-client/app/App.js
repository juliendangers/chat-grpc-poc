/*jshint maxcomplexity:8 */
/*global _, angular */
'use strict';

global.$ = require('jquery');
var angular = require('angular');
var Router = require('./Router');
var bootstrap = require('angular-bootstrap/ui-bootstrap-tpls');
var bindonce = require('angular-bindonce-publish-fork/bindonce');
var user = require('./user/user.module');

var services = require('./proto/chat_grpc_pb');
var grpc = require('grpc');
var chatService = () => {
  return new services.ChatServiceClient('localhost:9000', grpc.credentials.createInsecure());
};
var messages = require('./messages/messages.module')(bindonce, user, chatService);

angular
  .module('gRPC', [
    'pasvaz.bindonce',
    messages.name,
    user.name
  ]);

angular
  .module('gRPC')
  .controller('AppCtrl', ['$scope', require('./AppCtrl')]);

angular
  .module('gRPC')
  .config(['$stateProvider', '$urlRouterProvider', Router]);
