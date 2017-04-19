var presetEs2015 = require('babel-preset-es2015');

var browserifyOptions = {
  paths: ['./node_modules'],
  debug: true,
  cache: {},
  packageCache: {},
  entries: [config.build.js.app]
};

/**
 * build browserify bundle
 * @param bundle
 * @return {*}
 */
function buildBundle(bundle) {
  $.util.log($.util.colors.yellow('Building...'));
  var buildTimer = $.duration($.util.colors.green('Build finished'));
  return bundle.bundle()
    .on('error', onError)
    .pipe($.plumber({
      errorHandler: onError
    }))
    .pipe(source(config.build.js.dest.filename))
    .pipe(buffer())
    .pipe($.sourcemaps.init({loadMaps: true}))
    .pipe($.if(!isProd(), $.sourcemaps.write('./')))
    .pipe($.if(isProd(), $.sourcemaps.write(config.build.js.dest.sourcemaps.relativepath, {
      sourceMappingURL: function(file) {
        return config.build.js.dest.sourcemaps.endpoint + file.relative + '.map';
      }
    })))
    .pipe(gulp.dest(config.build.js.dest.path))
    .pipe(buildTimer);
}

var bundle = browserify(browserifyOptions)
  .transform(babelify.configure({
    presets: [presetEs2015]
  }));

gulp.task('build.js', function() {
  if (isWatch()) {
    bundle = watchify(bundle)
      .on('update', function() {
        $.util.log($.util.colors.green('Changes detected'));
        buildBundle(bundle);
      })
      .on('bytes', function(bytes) {
        if (bytes === 0) {
          return;
        }
        $.util.log($.util.colors.green('Done !'));
      });
  }
  return buildBundle(bundle);
});
