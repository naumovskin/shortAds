var leviApp = angular.module('leviApp.services', []);
// CATEGORY SERVICE
leviApp.service('categoryService', function($http) {
		
		this.url = 'api/categories';
		
		this.getOne = function(id) {
			return $http.get(this.url + '/' + id);
		};
		
		this.remove = function(id) {
			return $http.delete(this.url + '/' + id);
		};
		
		this.getAll = function() {
			return $http.get(this.url);
		};
		
		this.save = function(category) {
			if (category.id) {
				return $http.put(this.url + '/' + category.id, category);
			} else {
				return $http.post(this.url, category);
			}
		};
	});

//AD SERVICE

leviApp.service('adService', function($http) {
		
		this.url = 'api/ads';
		
		this.getOne = function(id) {
			return $http.get(this.url + '/' + id);
		};
		
		
		this.remove = function(id) {
			return $http.delete(this.url + '/' + id);
		};
		//'firstname': firstname,'lastname':lastname, 'page': page
		this.getAll = function() {
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