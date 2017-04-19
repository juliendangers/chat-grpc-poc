'use strict';

module.exports = function UserService() {
  var _user = {};

  return {
    setUser: function (user_id) {
      _user = {
        id: user_id
      };
    },

    getUser: function() {
      return _user;
    },

    getUserId: function () {
      return _user.id;
    }
  };
};
