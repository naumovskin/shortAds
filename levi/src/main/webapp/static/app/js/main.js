var leviApp = angular.module('leviApp', [
                                         'leviApp.services',
                                         'leviApp.controllers',
                                         'leviApp.routes']);


// CATEGORY CONTROLLER

	




		  



		

// za add i edit stranice da bude naziv posebno
leviApp.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);






