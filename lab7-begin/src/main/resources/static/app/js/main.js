var wafepaApp = angular.module('wafepaApp', ['ngRoute']);

wafepaApp.controller("HomeCtrl", function ($scope) {
	$scope.message = "Hello JWD43!";
});


wafepaApp.controller("ActivitiesCtrl", function ($scope, $http, $location) {

	var url = "/api/activities";

	$scope.activities = [];

	var getActivities = function () {
		var promise = $http.get(url);
		promise.then(
			function succes(res) {
				$scope.activities = res.data;
			},
			function error(res) {
				alert("greska pri dobavljanju");
			}
		);
	}

	getActivities();

	$scope.goToEdit = function (id) {
		$location.path('/activities/edit/' + id);
	}

	$scope.goToAdd = function () {
		$location.path('/activities/add/');
	}

	$scope.doDelete = function (id) {
		$http.delete("/api/activities/" + id).then(
			function uspeh() {
				getActivities();
			}, function neuspeh() {
				alert("neuspesan delete")
			});
	}

});

wafepaApp.controller("EditActivitiesCtrl", function ($scope, $routeParams, $http, $location) {

	var url = "/api/activities/" + $routeParams.id;

	$scope.activity = {};
	$scope.activity.name = "";

	var getActivity = function () {
		var promise = $http.get(url);
		promise.then(
			function succes(res) {
				$scope.activity = res.data;
			},
			function error(res) {
				alert("greska pri dobavljanju");
			}
		);
	};

	getActivity();

	$scope.doEdit = function () {
		$http.put(url, $scope.activity).then(
			function uspeh() {
				$location.path("/activities");
			}, function neuspeh() {
				alert("neuspesan edit")
			});
	}

});

wafepaApp.controller("AddActivityCtrl", function ($scope, $http, $location) {

	var url = "/api/activities";

	$scope.activity = {};
	$scope.activity.name = "";

	$scope.doAdd = function () {
		$http.post(url, $scope.activity).then(
			function uspeh() {
				$location.path("/activities");
			}, function neuspeh() {
				alert("neuspesan add")
			});
	};

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