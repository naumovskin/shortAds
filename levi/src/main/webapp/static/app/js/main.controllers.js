var leviApp = angular.module('leviApp.controllers', ['ui.bootstrap']);

// CATEGORY CONTROLLER

leviApp.controller('CategoryController', function($scope,$http,$location, $routeParams, categoryService) {
	
	// type ahead
//	  $scope.getLocation = function(val) {
//		    return $http.get('/api/activities', { params: { name : val,page : 0 }})
//		    .then(function(data){
//		    	$scope.activities = data;
//		      });
//		    };


	  $scope.bold = false;
	    $scope.jeste = false;
	 
	    $scope.bold = function(a){
	        if(a == $scope.page + 1){
	            $scope.jeste = true
	        }else{
	            $scope.jeste=false;
	        }
	        return $scope.jeste;
	    };
	   
	    $scope.range = function(min, max, step) {
	        step = step || 1;
	        var input = [];
	        for (var i = min + 1; i <= max; i += step) {
	            input.push(i);
	        }
	        return input;
	    };
	
	$scope.getAll = function() {
		
		categoryService.getAll($scope.searchDescription,$scope.searchText,$scope.page,$scope.number)
				.success(function(data,status,headers) {
					$scope.activities = data;
					$scope.hideSpinner = true;
					$scope.totalPages = headers('totalPages');
					$scope.totalNumOfElActivities = headers('totalNumOfElActivities');
				})
				.error(function() {
					$scope.hideSpinner = true;
					$scope.show_alert = true;
					
				});
	};
	// remove from edit page
	$removeFromEdit = function(id){
		categoryService.getOne(id)
			.success(function(data){
				categoryService.getAll();
			})
	};
	
	
	
	$scope.remove = function(id) {
		categoryService.getOne(id)
		.success(function(data) {
			$scope.activity = data;
		});
		
		categoryService.remove(id)
				.success(function() {
					$scope.getAll();
					$scope.showDeleteAlert = true;
				})
				.error(function() {
					$scope.showDeleteError = true;
					$scope.getAll();
				});
	};
	
	$scope.removeFromEdit = function(id) {
		categoryService.getOne(id)
		.success(function(data) {
			$scope.category = data;
		});
		
		categoryService.remove(id)
				.success(function() {
					$location.path('/categories');
				})
				.error(function() {
					$scope.showDeleteError = true;
				});
	};
	
	$scope.initCategories = function() {
		$scope.category = {};
			categoryService.getAll()
					.success(function(data) {
						$scope.category = data;
						$scope.hideSpinner = true;
					})
					.error(function() {
						$scope.hideSpinner = true;
						$scope.show_alert = true;
					});
	};
	
	$scope.saveCategory = function() {
		categoryService.save($scope.category)
					.success(function() {
						$location.path('/categories');
					})
					.error(function() {
						$scope.show_alert = true;
						console.log('error');
					});
		
	};
	
	
	 $scope.location = $location;
	
	
	
	
	
});



//AD CONTROLLER
leviApp.controller('AdController', function($scope,$location,$routeParams,adService) {

	$scope.bold = false;
	    $scope.jeste = false;
	 
	    $scope.bold = function(a){
	        if(a == $scope.page + 1){
	            $scope.jeste = true
	        }else{
	            $scope.jeste=false;
	        }
	        return $scope.jeste;
	    };
	   
	    $scope.range = function(min, max, step) {
	        step = step || 1;
	        var input = [];
	        for (var i = min + 1; i <= max; i += step) {
	            input.push(i);
	        }
	        return input;
	    };
	
		$scope.getAll = function() {
			adService.getAll($scope.page, $scope.category, $scope.expiryDate, $scope.username, $scope.user)
					.success(function(data, status, headers) {
						$scope.ads = data;
						$scope.hideSpinner = true;
						$scope.totalPages = headers('total-pages');
						$scope.myAds = headers('myAds');
						
//						$scope.filteredAds = [];
//						
//						$scope.currentPage = 1;
//						$scope.numPerPage = 2;
//						$scope.maxSize = 5;
//						
//						$scope.$watch('currentPage + numPerPage', function() {
//							   
//							var begin = (($scope.currentPage - 1) * $scope.numPerPage);
//						    var end = begin + $scope.numPerPage;
//						   
//						    $scope.filteredAds = $scope.ads.slice(begin, end);
//						  });

					})
					.error(function() {
						$scope.showError = true;
						$scope.hideSpinner = true;
					});
		};
	
	// gledamo da li vec postoji korisnik koji se isto zove
	
	$scope.findIfExistsUser = function() {
		// u scope treba da ide ime i prezime korisnika
		//$scope.promenjiva = '#myModal';
		
		adService.findIfExistsUser($scope.ad.firstname, $scope.ad.lastname)
				.success(function(data) {
					$scope.promenjiva = false;
					adService.save($scope.ad)
					$location.path('/users');
				})
				.error(function() {
					
					$scope.show_alert=true;
					
				});
	};
	
	
	
	
	
	
	$scope.remove = function(id) {
		adService.remove(id)
				.success(function() {
					$scope.getAll();
					$scope.hideSpinner = true;
				})
				.error(function() {
					$scope.hideSpinner = true;
					$scope.show_alert = true;

				});
	};
	
	$scope.initAd = function() {
		$scope.ad = {};
		
		if ($routeParams.id) { // edit stranica
			adService.getOne($routeParams.id)
					.success(function(data) {
						$scope.ad = data;
						$scope.hideSpinner = true;
					})
					.error(function() {
						$scope.hideSpinner = true;
						$scope.show_alert = true;

					});
		}
		else {
			adService.getAll()
				.success(function(data){
					$scope.ad = data;
				})
				.error(function(data){
					$scope.show_alert = true;
				});
		}
	};
	
	$scope.saveAd = function() {
		adService.save($scope.ad)
				.success(function() {
					$location.path('/ads');
				})
				.error(function() {

				});
	};
	
	
	// USER DATEPICKER
	
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function() {
		    $scope.dt = null;
		  };

		  $scope.inlineOptions = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: true
		  };

		  $scope.dateOptions = {
		    dateDisabled: disabled,
		    formatYear: 'yy',
		    maxDate: new Date(2020, 5, 22),
		    minDate: new Date(),
		    startingDay: 1
		  };

		  // Disable weekend selection
		  function disabled(data) {
		    var date = data.date,
		      mode = data.mode;
		    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
		  }

		  $scope.toggleMin = function() {
		    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
		    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
		  };

		  $scope.toggleMin();

		  $scope.open1 = function() {
		    $scope.popup1.opened = true;
		  };

		  $scope.open2 = function() {
		    $scope.popup2.opened = true;
		  };

		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };

		  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		  $scope.format = $scope.formats[2];
		  $scope.altInputFormats = ['M!/d!/yyyy'];

		  $scope.popup1 = {
		    opened: false
		  };

		  $scope.popup2 = {
		    opened: false
		  };

		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		  afterTomorrow.setDate(tomorrow.getDate() + 1);
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		    },
		    {
		      date: afterTomorrow,
		      status: 'partially'
		    }
		  ];

		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i = 0; i < $scope.events.length; i++) {
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  }
	
		  

		  
//angular.module('ui.bootstrap.demo').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {
//			  //
//			      $scope.items = items;
//			      $scope.selected = {
//			        item: $scope.items[0]
//			      };
////
			      $scope.ok = function () {
			        $uibModalInstance.close();
			      };

			      $scope.cancel = function () {
			        $uibModalInstance.dismiss('cancel');
			      };
		  
//		  
			      $scope.location = $location;
		  
		  
});

// NAVIGATION CONTROLLER

leviApp.controller('navigation', function($rootScope, $scope, $http, $location, $route, $window){
	
//	$scope.tab = function(route) {
//		return $route.current && route === $route.current.controller;
//	};

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":" + credentials.password)
		} : {};

		$http.get('user', {
			headers : headers
		}).success(function(data) {
			if (data.name) {
				$rootScope.currentUser = data.principal;
				$scope.user = data;
			} else {
				$rootScope.currentUser = null;
			}
			callback && callback($rootScope.currentUser, data);
		}).error(function(data) {
			$rootScope.currentUser = null;
			callback && callback(false, data);
		});

	}

	authenticate();

	$scope.credentials = {};
	
	$scope.login = function() {
		authenticate($scope.credentials, function(currentUser, message) {
			if (currentUser) {
				console.log("Login succeeded");
				$location.path("/");
				$window.location.reload();
			} else {
				console.log("Login failed");
				$location.path("/login");
				$scope.error = true;
//				if (message) {
//					NotificationService.statusBarError(message);
//				} else {
//					NotificationService.statusBarError("There was a problem logging in. Please try again.");
//				}
				$rootScope.currentUser = null;
			}
		})
	};

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.currentUser = null;
			$location.path("/");
		}).error(function(data) {
			console.log("Logout failed");
			$rootScope.currentUser = null;
		});
	}
	
	
});
