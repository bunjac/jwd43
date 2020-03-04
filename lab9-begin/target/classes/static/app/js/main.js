var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.controller("HomeCtrl", function ($scope) {
	$scope.message = "Hello JWD43!";
});

wafepaApp.controller("ActivitiesCtrl", function ($scope, $http, $location) {

	var url = "/api/activities";

	$scope.activities = [];



	var getActivities = function () {
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.activities = res.data;

				//console.log(res);
			},
			function error(res) {

				alert("Couldn't fetch activities.");
			}
		);
		//console.log("test");
	}

	getActivities();

	$scope.goToEdit = function (id) {
		$location.path("/activities/edit/" + id);
	}

	$scope.goToAdd = function () {
		$location.path("/activities/add");
	}

	$scope.doDelete = function (id) {
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success() {
				getActivities();
			},
			function error() {
				alert("Couldn't delete the activity");
			}
		);
	}

});


wafepaApp.controller("EditActivityCtrl", function ($scope, $http, $routeParams, $location) {
	//console.log($routeParams);

	var url = "/api/activities/" + $routeParams.aid;

	$scope.activity = {};
	$scope.activity.name = "";

	var getActivity = function () {
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg) {
				$scope.activity = odg.data;
			},
			function neuspeh(odg) {
				alert("Couldn't fetch the activity.");
			}
		);
	}

	getActivity();

	$scope.doEdit = function () {
		var promise = $http.put(url, $scope.activity);
		promise.then(
			function success(res) {
				$location.path("/activities");
			},
			function error() {
				alert("Couldn't save the activity");
			}
		);
	}

});


wafepaApp.controller("AddActivityCtrl", function ($scope, $http, $location) {

	$scope.activity = {};
	$scope.activity.name = "";

	var url = "/api/activities";

	$scope.doAdd = function () {
		$http.post(url, $scope.activity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Couldn't save the activity.");
			}
		);
	}

});




wafepaApp.controller("RecordsCtrl", function ($scope, $http, $location) {

	var url = "/api/records";
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users"

	$scope.records = [];
	$scope.activities = [];
	$scope.users = [];

	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.minDuration = "";
	$scope.newRecord.intensity = "";
	//TODO: dodati obeležja kojim se povezuje sa korisnikom i aktivnošću
	$scope.newRecord.userId = "";
	$scope.newRecord.activityId = "";


	$scope.searchRecord = {};
	$scope.searchRecord.activityName = "";
	$scope.searchRecord.minDuration = "";
	$scope.searchRecord.intensity = "";

	$scope.pageNum = 0;
	$scope.totalPages = 1;

	$scope.doPage = function (x) {
		$scope.pageNum += x;
		getRecords();
	};

	$scope.prikaziObavestenje = false;

	var getRecords = function () {
		var config = { params: {} };
		if ($scope.searchRecord.activityName != "") {
			config.params.activityName = $scope.searchRecord.activityName;
		}
		if ($scope.searchRecord.minDuration != "") {
			config.params.minDuration = $scope.searchRecord.minDuration;
		}
		if ($scope.searchRecord.intensity != "") {
			config.params.intensity = $scope.searchRecord.intensity;
		}

		config.params.pageNum = $scope.pageNum;
		var promise = $http.get(url, config);
		promise.then(
			function success(res) {
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");
				var brElem = $scope.records.length;
				for (i = 1; i <= 5-brElem; i++) {
					$scope.records.push({});
				}
			},
			function error() {
				alert("Couldn't fetch records");
			}
		);
	}

	getRecords();

	//TODO: Obezbediti prihvat korisnika i aktivnosti

	var getActivities = function () {
		$http.get(activitiesUrl).then(
			function success(res) {
				$scope.activities = res.data;
			},
			function error() {
				alert("Couldn't fetch activities");
			}
		);
	}

	var getUsers = function () {
		return $http.get(usersUrl).then(
			function success(res) {
				$scope.users = res.data;
			},
			function error() {
				alert("Couldn't fetch users.");
			}
		);
	}

	getActivities();
	getUsers();

	$scope.doAdd = function () {

		$http.post(url, $scope.newRecord).then(
			function success(res) {
				getRecords();
				$scope.prikaziObavestenje = true;
			},
			function error() {
				$scope.prikaziObavestenje = false;
				alert("Couldn't save the record");
			}
		);
	}

	$scope.goToEdit = function (id) {
		$location.path("/records/edit/" + id);
	}


	$scope.doSearch = function () {
		$scope.pageNum = 0;
		getRecords();
	};

	$scope.doSearchMin30Min = function () {
		$scope.pageNum = 0;
		$scope.searchRecord.activityName = "";
		$scope.searchRecord.minDuration = "30";
		$scope.searchRecord.intensity = "";
		getRecords();
	};
});


wafepaApp.controller("EditRecordCtrl", function ($scope, $http, $routeParams, $location) {

	var recordUrl = "/api/records/" + $routeParams.id;
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users";

	$scope.record = {};
	$scope.record.time = "";
	$scope.record.duration = "";
	$scope.record.intensity = "";
	$scope.record.userId = "";
	$scope.record.activityId = "";


	$scope.activities = [];
	$scope.users = [];



	var getActivities = function () {
		$http.get(activitiesUrl).then(
			function success(res) {
				$scope.activities = res.data;
				getUsers();
			},
			function error() {
				alert("Couldn't fetch activities");
			}
		);
	}

	var getUsers = function () {
		return $http.get(usersUrl).then(
			function success(res) {
				$scope.users = res.data;
				getRecord();
			},
			function error() {
				alert("Couldn't fetch users.");
			}
		);
	}

	var getRecord = function () {
		$http.get(recordUrl).then(
			function success(res) {
				$scope.record = res.data;
			},
			function error() {
				alert("Couldn't fetch record.");
			}
		);
	}

	//TODO: Obezbediti redosled izvrsavanja!
	getActivities();


	$scope.doEdit = function () {
		$http.put(recordUrl, $scope.record).then(
			function success() {
				$location.path("/records");
			},
			function error() {
				alert("Something went wrong.");
			}
		);
	}
});




wafepaApp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: '/app/html/home.html',
			controller: "HomeCtrl"
		})
		.when('/activities', {
			templateUrl: '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl: '/app/html/add-activity.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl: '/app/html/edit-activity.html'
		})
		.when('/records', {
			templateUrl: '/app/html/records.html'
		})
		.when('/records/edit/:id', {
			templateUrl: '/app/html/edit-record.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
