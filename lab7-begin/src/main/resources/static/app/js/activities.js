var wafepaApp = angular.module('wafepaApp');

wafepaApp.controller("ActivitiesCtrl", function ($scope, $http, $location) {

	var url = "/api/activities";

	$scope.activities = [];

	$scope.orderTip = '+';
	$scope.drugiSort = 'desc';

	$scope.doReverse = function () {
		if ($scope.orderTip == '-') {
			$scope.orderTip = '+';
			$scope.drugiSort = 'desc';
		} else {
			$scope.orderTip = '-';
			$scope.drugiSort = 'asc';
		}
	}

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

	$scope.doView = function (id) {
		$location.path("/activities/view/" + id);
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

	$scope.disabled = true;

	$scope.doAdd = function () {
		$http.post(url, $scope.activity).then(
			function uspeh() {
				$location.path("/activities");
			}, function neuspeh() {
				alert("neuspesan add")
			});
	};

	$scope.kontrolisiDugme = function () {
		if ($scope.activity.name == "") {
			$scope.disabled = true;
		} else {
			$scope.disabled = false;
		}
	}

});