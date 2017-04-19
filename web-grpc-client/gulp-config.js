'use strict';

module.exports = {
  build: {
    js: {
      vendors: [],
      base: [],
      app: './app/app.js',
      dest: {
        filename: 'grpc.js',
        path: './public/js/',
        sourcemaps: {
          path: 'public/sourcemaps/',
          relativepath: '../sourcemaps/',
          endpoint: '/sourcemaps/?name='
        }
      }
    },
    less: {
      base: {
        src: [
          'app/**/*.less'
        ],
        watch: [],
        dest: './public/css'
      }
    }
  }
};
