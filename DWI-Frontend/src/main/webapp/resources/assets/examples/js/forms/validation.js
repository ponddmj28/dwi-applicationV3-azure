(function (global, factory) {
  if (typeof define === "function" && define.amd) {
    define('/forms/validation', ['jquery', 'Site'], factory);
  } else if (typeof exports !== "undefined") {
    factory(require('jquery'), require('Site'));
  } else {
    var mod = {
      exports: {}
    };
    factory(global.jQuery, global.Site);
    global.formsValidation = mod.exports;
  }
})(this, function (_jquery, _Site) {
  'use strict';

  var _jquery2 = babelHelpers.interopRequireDefault(_jquery);

  (0, _jquery2.default)(document).ready(function ($$$1) {
    (0, _Site.run)();
  });

  // Example Validataion Standard Mode
  // ---------------------------------
  (function () {
    (0, _jquery2.default)('#formLogin').formValidation({
      framework: "bootstrap4",
      button: {
        selector: '#submitLogin',
        disabled: 'disabled'
      },
      icon: null,
      fields: {
    	  username: {
          validators: {
            notEmpty: {
              message: 'The username is required and cannot be empty'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: 'The password is required and cannot be empty'
            }
          }
        }
      },
      err: {
        clazz: 'invalid-feedback'
      },
      control: {
        // The CSS class for valid control
        valid: 'is-valid',

        // The CSS class for invalid control
        invalid: 'is-invalid'
      },
      row: {
        invalid: 'has-danger'
      }
    });
  })();

});