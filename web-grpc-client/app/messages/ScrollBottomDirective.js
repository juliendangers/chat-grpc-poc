'use strict';

var _ = require('lodash');

module.exports = function(){
  return {
    restrict: 'A',
    scope: {
      scrollElements: '='
    },
    link: function(scope, element, attrs) {
      var $el= $(attrs.scrollClass);
      scope.$watch('scrollElements', function(newValue, oldValue) {
        if (newValue && !_.isEmpty($el)) {
          $el.scrollTop($el[0].scrollHeight);
        }
      }, true);
    }
  };
};
