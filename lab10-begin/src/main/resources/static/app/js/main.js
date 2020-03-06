var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope){
	$scope.message="Hello JWD43!";
});





wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: "HomeCtrl"
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.when('/records/edit/:id', {
			templateUrl : '/app/html/edit-record.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
