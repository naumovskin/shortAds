var leviApp = angular.module('leviApp', ['ngRoute','ui.bootstrap']);


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
			$scope.activity = data;
		});
		
		categoryService.remove(id)
				.success(function() {
					$location.path('/categories');
				})
				.error(function() {
					$scope.showDeleteError = true;
				});
	};
	
	$scope.initActivity = function() {
		$scope.activity = {};
		if ($routeParams.id) { // edit stranica
			categoryService.getOne($routeParams.id)
					.success(function(data) {
						$scope.activity = data;
						$scope.hideSpinner = true;
					})
					.error(function() {
						$scope.hideSpinner = true;
						$scope.show_alert = true;
					});
		}
	};
	
	$scope.saveActivity = function() {
		categoryService.save($scope.activity)
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
	

// AD CONTROLLER
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
	
	//, {params: {'firstname': firstname,'lastname':lastname}}
	$scope.getAll = function() {
		// $scope.search u getAll kao parameter
		adService.getAll($scope.searchText,$scope.page)
				.success(function(data,status,headers) {
					$scope.ads = data;
					$scope.hideSpinner = true;
					$scope.totalPages = headers('totalPages');
					$scope.totalNumOfElUsers = headers('totalNumOfElUsers');

				})
				.error(function() {
					$scope.hideSpinner = true;
					$scope.show_alert = true;

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
	};
	
	$scope.saveUser = function() {
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
		  
		  
		  
});



// ovde kao konfig kazemo da nasa aplikacija pod kljucem route provider koristi
// sledeci mapping
leviApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/categories', {
            templateUrl : '/static/app/html/partial/categories.html',
            controller: 'CategoryController'
        })
        .when('/categories/add', {
        	title : 'Add',
        	templateUrl : '/static/app/html/partial/addEditCategory.html',
            controller: 'CategoryController'
        })
        .when('/categories/edit/:id', {
        	title : 'Edit',
            templateUrl : '/static/app/html/partial/addEditCategory.html',
            controller: 'CategoryController'
        })
        // view activities
        .when('/categories/view/:id', {
            templateUrl : '/static/app/html/partial/viewCategory.html',
            controller: 'CategoryController'
        })
        .when('/ads', {
            templateUrl : '/static/app/html/partial/ads.html',
            controller: 'AdController'
        })
        .when('/ads/add', {
            templateUrl : '/static/app/html/partial/addEditAd.html',
            controller: 'AdController'
        })
        .when('/ads/edit/:id', {
            templateUrl : '/static/app/html/partial/addEditAd.html',
            controller: 'AdController'
        })
        .when('/countries', {
            templateUrl : '/static/app/html/partial/countries.html',
            controller: 'CountriesController'
        })
        
        .otherwise({
            redirectTo: '/'
        });
}]);
		  
// AD SERVICE

leviApp.service('adService', function($http) {
		
		this.url = 'api/ads';
		
		this.getOne = function(id) {
			return $http.get(this.url + '/' + id);
		};
		
		// gledamo da li postoji korisnik s imenom kao novo-registrovani
		this.findIfExistsUser = function(firstname,lastname){
			
			return $http.get(this.url + '/get',{params : {'firstname' : firstname, 'lastname' : lastname}});
			console.log(user.firstname,user.lastname);
		};
		
		this.remove = function(id) {
			return $http.delete(this.url + '/' + id);
		};
		//'firstname': firstname,'lastname':lastname, 'page': page
		this.getAll = function(name,page) {
			return $http.get(this.url);
		};
		
		this.save = function(user) {
			if (user.id) {
				return $http.put(this.url + '/' + user.id, user);
			} else {
				return $http.post(this.url, user);
			}
		};
	});


// CATEGORY SERVICE
leviApp.service('categoryService', function($http) {
		
		this.url = 'api/categories';
		
		this.getOne = function(id) {
			return $http.get(this.url + '/' + id);
		};
		
		this.remove = function(id) {
			return $http.delete(this.url + '/' + id);
		};
		
		this.getAll = function(description, name, page, number) {
			return $http.get(this.url, { params: {'description':description , 'name': name , 'page': page, 'number': number}});
		};
		
		this.save = function(activity) {
			if (activity.id) {
				return $http.put(this.url + '/' + activity.id, activity);
			} else {
				return $http.post(this.url, activity);
			}
		};
	});
		

// za add i edit stranice da bude naziv posebno
leviApp.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);






