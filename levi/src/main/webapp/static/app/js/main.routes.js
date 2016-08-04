var leviApp = angular.module('leviApp.routes', ['ngRoute']);

// ovde kao konfig kazemo da nasa aplikacija pod kljucem route provider koristi
// sledeci mapping
leviApp.config(function($routeProvider,$httpProvider) {
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
        .when('/myAds', {
		templateUrl : '/static/app/html/partial/currentAds.html',
		controller : 'AdController'
        })
        .when('/login', {
		templateUrl : '/static/app/html/partial/login.html',
		controller : 'navigation'
        })
        
        .when('/register', {
		templateUrl : '/static/app/html/partial/register.html',
		controller : 'navigation'
        })
        
        .when('/editProfile', {
		templateUrl : '/static/app/html/partial/editProfile.html',
		controller : 'navigation'
        })
        
        .otherwise({
            redirectTo: '/'
        });
    
    	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});