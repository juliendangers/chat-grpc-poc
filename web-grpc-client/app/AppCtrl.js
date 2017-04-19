/*jshint maxparams:6 */
'use strict';

module.exports = function ($scope) {

  // listen to ui-router resolve errors
  $scope.$on('$stateChangeError', function (evt, to, toParams, from, fromParams, error) {
    console.error('ui-router error: ' + error, error.stack, error.data, 'toParams', toParams);

    console.table({
      evt: evt
    });

    console.table({
      error: error
    });

    console.table({
      toParams: toParams
    });

    console.table({
      fromParams: fromParams
    });
  });
};
