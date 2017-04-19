'use strict';

var UserService = require('./UserService')();
var expect = require('chai').expect;

describe('User factory', function() {
  it('should expose the following interface', function() {
    expect(UserService).to.have.all.keys('getUser', 'setUser', 'getUserId');
  });

  it('should return the right userId', function() {
    var user_id = 'test';
    UserService.setUser(user_id);
    expect(UserService.getUserId()).to.equal(user_id);
  });
});
