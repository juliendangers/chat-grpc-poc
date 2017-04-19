/*jshint maxcomplexity:7 */
/* global _, uuid */

'use strict';

var _ = require('lodash');
var uuid = require('uuid');

function Router($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise('/users/' + uuid.v4());

  $stateProvider.state('users', {
    url: "/users/{user_id}",
    //abstract: true,
    template: "<div class='grpc-chat' ui-view autoscroll='false'></div>",
    resolve: {
      user: ['$stateParams', 'UserService',
        function ($stateParams, UserService) {
          // First we need to set the user
          return UserService.setUser($stateParams.user_id);
        }]
    },
    controller: ['$scope', '$state', 'user',
      function ($scope, $state) {
        // Only redirect to 'users.conversations' if 'users' was accessed directly
        if ($state.is('users')) {
          $state.go('users.messages');
        }
      }]
  });

  $stateProvider.state('users.messages', {
    url: "/messages",
    templateUrl: "./messages/messages.html",
    controller: 'MessagesCtrl',
    controllerAs: 'MessagesCtrl'
  });

  $stateProvider.state('logout', {
    url: "/",
    templateUrl: "./logout.html"
  });

}
module.exports = Router;
