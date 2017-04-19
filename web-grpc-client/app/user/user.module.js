'use strict';

var angular = require('angular');
var UserService = require('./UserService');

module.exports = angular
  .module('users', [])
  .factory('UserService', UserService);
