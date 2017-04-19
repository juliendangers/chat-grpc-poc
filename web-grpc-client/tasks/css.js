var runSequence = require('run-sequence');
var builds = ['base'];
var buildLessEntry = function(options) {
  return gulp.src(options.src)
    .pipe($.plumber({errorHandler: onError}))
    .pipe($.less())
    .pipe($.concat('style.css'))
    .pipe(gulp.dest(options.dest));
};

var lessTasks = _.map(builds, function(build) {
  var task = 'build.less.' + build;
  gulp.task(task + '.task', function() {
    return buildLessEntry(config.build.less[build], build);
  });

  gulp.task(task, function() {
    if (isWatch()) {
      return gulp.watch(config.build.less[build].watch, [task + '.task']);
    } else {
      return buildLessEntry(config.build.less[build]);
    }
  });

  return task;
});

gulp.task('build.less', function(callback) {
  return runSequence(lessTasks, callback);
});
