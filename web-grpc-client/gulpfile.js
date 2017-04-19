'use strict';

require('./gulp-bootstrap.js');
require('./tasks');

/**
 * Gulp task build files and clean useless files
 */
gulp.task('default', ['build.js', 'build.less']);
