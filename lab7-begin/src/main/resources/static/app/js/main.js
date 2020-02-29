var wafepaApp = angular.module('wafepaApp', ['ngRoute']);

angular.module('wafepaApp').controller("HomeCtrl", function ($scope) {
	$scope.message = "Hello JWD43!";
});


wafepaApp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: '/app/html/home.html'
			//controller: "HomeCtrl"
		})
		.when('/activities', {
			templateUrl: '/app/html/activities.html'
		})
		.when('/activities/edit/:id', {
			templateUrl: '/app/html/edit-activities.html'
		})
		.when('/activities/add', {
			templateUrl: '/app/html/add-activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);