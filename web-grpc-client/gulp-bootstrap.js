global._ = require('lodash');
global.gulp = require('gulp');
global.$ = require('gulp-load-plugins')();
global.babelify = require('babelify');
global.watchify = require('watchify');
global.browserify = require('browserify');
global.source = require('vinyl-source-stream');
global.buffer = require('vinyl-buffer');
global.argv = require('yargs').argv;

/**
 * Print error in console
 * @param {Object} err
 */
global.onError = function onError(err) {
  $.util.log($.util.colors.red(err));
  if (isProd()) {
    process.exit(1);
  } else {
    this.emit('end');
  }
};

/**
 * return true if argv env is prod
 * @return {boolean}
 */
global.isProd = function isProd() {
  return !!argv.prod;
};

/**
 * is the flag watch provided
 * @return {boolean}
 */
global.isWatch = function() {
  return !!argv.watch;
};

$.util.log($.util.colors.yellow('Build prod version: ', isProd()));
$.util.log($.util.colors.yellow('Build and watch: ', isWatch()));

global.config = require('./gulp-config');
