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
	  

	  (function () {
		    (0, _jquery2.default)('#exampleStandardForm').formValidation({
		      framework: "bootstrap4",
		      button: {
		        selector: '#validateButton',
		        disabled: 'disabled'
		      },
		      icon: null,
		      fields: {
		    	  firstName: {
		          validators: {
		            notEmpty: {
		              message: 'The first name is required and cannot be empty'
		            }
		          }
		        },
		        lastName: {
		          validators: {
		            notEmpty: {
		              message: 'The lastname is required and cannot be empty'
		            }
		          }
		        },
		        idCard: {
		          validators: {
		            notEmpty: {
		              message: 'The id card no is required and cannot be empty'
		            },
		            integer: {
		                message: 'The value is not an number'
		            },
		            stringLength: {
		              max: 13,
		              min: 13,
		              message: 'The id card no must be 13 legits'
		            }
		          }
		        },
		        phoneNo1: {
			          validators: {
			            notEmpty: {
			              message: 'The id card no is required and cannot be empty'
			            },
			            integer: {
			                message: 'The value is not an number'
			            },
			            stringLength: {
			              max: 10,
			              min: 9,
			              message: 'The id card no must be 9 or 10 legits'
			            }
			          }
			    },
		        phoneNo2: {
			          validators: {
			            integer: {
			                message: 'The value is not an number'
			            },
			            stringLength: {
			              max: 10,
			              min: 9,
			              message: 'The id card no must be 9 or 10 legits'
			            }
			          }
			    },
			    targetPackage: {
			          validators: {
			            notEmpty: {
			              message: 'The package is required and cannot be empty'
			            }
			          }
			    },
			    installDate: {
			          validators: {
			            notEmpty: {
			              message: 'The package is required and cannot be empty'
			            }
			          }
			    },
			    customerAddress: {
			          validators: {
			            notEmpty: {
			              message: 'The customerAddress is required and cannot be empty'
			            }
			          }
			    },
			    nmLine: {
			          validators: {
			            notEmpty: {
			              message: 'The Line Name is required and cannot be empty'
			            }
			          }
			    },
			    targetPackageDesc: {
			          validators: {
				            notEmpty: {
				              message: 'The Line Name is required and cannot be empty'
				            }
				          }
				 },
			    upload1: {
			          validators: {
			            notEmpty: {
			              message: 'The upload1 is required and cannot be empty'
			            },
			            file:{
			            	extension: 'doc,pdf,jpeg,jpg,png,bmp',
			            	type:'application/msword,application/pdf,image/jpeg,image/png,image/x-ms-bmp',
			            	message: 'file type must be doc,pdf,jpeg,jpg,png and bmp only '
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
	
	  
	  (function () {
		    (0, _jquery2.default)('#exampleStandardForm2').formValidation({
		      framework: "bootstrap4",
		      button: {
		        selector: '#validate2Button',
		        disabled: 'disabled'
		      },
		      icon: null,
		      fields: {
		    	  circuitNo: {
		          validators: {
		            notEmpty: {
		              message: 'The circuit no is required and cannot be empty'
		            },
		            integer: {
		                message: 'The circuit no not an number'
		            },
		            stringLength: {
			              max: 10,
			              min: 9,
			              message: 'The circuit no must be 9 or 10 legits'
			        }
		          }
		        },
		        duedateInstallTmp: {
			          validators: {
			            notEmpty: {
			              message: 'The Install Date is required and cannot be empty'
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
	  
	  $('#validate2Button').click(function() {
		  var message = 'ยืนยันเลขงานติดตั้ง : ';;
			 message = message + $("input[name='circuitNo']").val() +'<BR>';
			 message = message + 'นัดติดตั้งวันที่ :';
			 message = message + $("input[name='duedateInstallTmp']").val()+'<BR>';
			 message = message + 'งานในพื้นที่ กทม และ ปฎิมณฑล : ';
			 message = message + $("input[name='isItInAreaTmp']:checked").val()+'<BR>';
			 $("p[name='messageConfirm2']").html(message);
		});

		$('#validate2Btn').click(function(){
		     /* when the submit button in the modal is clicked, submit the form */
		    $('#exampleStandardForm2').submit();
		});
		
		$('#closed2Btn').click(function(){
			$('#validate2Button').removeClass( "disabled" )
		});
		
		
		(function () {
		    (0, _jquery2.default)('#exampleStandardForm3').formValidation({
		      framework: "bootstrap4",
		      button: {
		        selector: '#validate3Button',
		        disabled: 'disabled'
		      },
		      icon: null,
		      fields: {
		    	  status: {
		          validators: {
		            notEmpty: {
		              message: 'The status is required and cannot be empty'
		            }
		          }
		    	},
		    	circuitNo: {
		             validators: {
			            notEmpty: {
			              message: 'The circuit no is required and cannot be empty'
			            },
			            integer: {
			                message: 'The circuit no not an number'
			            },
			            stringLength: {
				              max: 10,
				              min: 9,
				              message: 'The circuit no must be 9 or 10 legits'
				        }
			          }
			        },
		        duedateInstallTmp: {
			          validators: {
			            notEmpty: {
			              message: 'The Install Date is required and cannot be empty'
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
		
		 $('#validate3Button').click(function() {
			 var message = 'ยืนยันเลขงานติดตั้ง : ';;
			 message = message + $("input[name='circuitNo']").val() +'<BR>';
			 message = message + 'ปิดงานวันที่ :';
			 message = message + $("input[name='duedateInstallTmp']").val()+'<BR>';
			 message = message + 'งานในพื้นที่ กทม และ ปฎิมณฑล : ';
			 message = message + $("input[name='isItInAreaTmp']:checked").val()+'<BR>';
			 message = message + 'ต้องการที่จะ ';
			 message = message + $("select[name='status']").val()+'<BR>';
			 $("p[name='messageConfirm3']").html(message);
			});

			$('#validate3Btn').click(function(){
			     /* when the submit button in the modal is clicked, submit the form */
			    $('#exampleStandardForm3').submit();
			});
			
			$('#closed3Btn').click(function(){
				$('#validate3Button').removeClass( "disabled" )
			});
});