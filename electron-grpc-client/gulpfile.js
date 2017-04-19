const gulp = require('gulp');
const util = require('gulp-util');
const plumber = require('gulp-plumber');
const less = require('gulp-less');
const concat = require('gulp-concat');

/**
 * Print error in console
 * @param {Object} err
 */
function onError(err) {
  util.log(util.colors.red(err));
  this.emit('end');
}

let options = {
  src: [
    'src/**/*.less'
  ],
  dest: 'src/assets/css'
};

let buildLessEntry = function(options) {
  return gulp.src(options.src)
    .pipe(plumber({errorHandler: onError}))
    .pipe(less())
    .pipe(concat('style.css'))
    .pipe(gulp.dest(options.dest));
};

gulp.task('less', function() {
  return buildLessEntry(options);
});

gulp.task('default', ['less']);
