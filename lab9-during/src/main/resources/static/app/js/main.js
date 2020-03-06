var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope){
	$scope.message="Hello JWD43!";
});

wafepaApp.controller("ActivitiesCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function(){
		var promise = $http.get(url);
		promise.then(
			function success(res){
				$scope.activities = res.data;
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch activities.");
			}
		);
		//console.log("test");
	}
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.goToAdd = function(){
		$location.path("/activities/add");
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Couldn't delete the activity");
			}
		);
	}
	
});


wafepaApp.controller("EditActivityCtrl", function($scope,$http, $routeParams, $location){
	//console.log($routeParams);
	
	var url = "/api/activities/" + $routeParams.aid;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(odg){
				alert("Couldn't fetch the activity.");
			}
		);
	}
	
	getActivity();
	
	$scope.doEdit = function(){
		var promise = $http.put(url, $scope.activity);
		promise.then(
			function success(res){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't save the activity");
			}
		);
	}
	
});


wafepaApp.controller("AddActivityCtrl", function($scope, $http, $location){
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var url = "/api/activities";
	
	$scope.doAdd = function(){
		$http.post(url, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't save the activity.");
			}
		);
	}
	
});




wafepaApp.controller("RecordsCtrl", function($scope, $http, $location){
	
	var url = "/api/records";
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users" 
		
	$scope.records = [];
	$scope.activities = [];
	$scope.users = [];
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	//TODO: dodati obeležja kojim se povezuje sa korisnikom i aktivnošću
	$scope.newRecord.userId = "";
	$scope.newRecord.activityId = "";
	
	
	$scope.search = {};
	$scope.search.activityName = "";
	$scope.search.minDuration = "";
	$scope.search.intensity = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getRecords = function(){
		
		var config = { params:{}};
		
		if($scope.search.activityName != ""){
			config.params.activityName = $scope.search.activityName;
		}
		if($scope.search.minDuration != ""){
			config.params.minDuration = $scope.search.minDuration;
		}
		if($scope.search.intensity != ""){
			config.params.intensity = $scope.search.intensity; 
		}
		
		config.params.pageNum = $scope.pageNum;
		
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch records");
			}
		);
	}
	
	getRecords();
	
	//TODO: Obezbediti prihvat korisnika i aktivnosti
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	var getUsers = function(){
		return $http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
			},
			function error(){
				alert("Couldn't fetch users.");
			}
		);
	}
	
	getActivities();
	getUsers();
	
	$scope.doAdd = function(){
		
		$http.post(url, $scope.newRecord).then(
			function success(res){
				getRecords();
				
				$scope.newRecord = {};
				$scope.newRecord.time = "";
				$scope.newRecord.duration = "";
				$scope.newRecord.intensity = "";
				//TODO: dodati obeležja kojim se povezuje sa korisnikom i aktivnošću
				$scope.newRecord.userId = "";
				$scope.newRecord.activityId = "";
			},
			function error(){
				alert("Couldn't save the record");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/records/edit/" + id);
	}
	
	$scope.doSearch = function(){
		//console.log($scope.search);
		$scope.pageNum = 0;
		getRecords();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getRecords();
	}
	
});


wafepaApp.controller("EditRecordCtrl", function($scope, $http, $routeParams, $location){
	
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
	
	
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
				getUsers();
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	var getUsers = function(){
		return $http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
				getRecord();
			},
			function error(){
				alert("Couldn't fetch users.");
			}
		);
	}
	
	var getRecord = function(){
		$http.get(recordUrl).then(
			function success(res){
				$scope.record = res.data;
			},
			function error(){
				alert("Couldn't fetch record.");
			}
		);
	}
	
	//TODO: Obezbediti redosled izvrsavanja!
	getActivities();
	
	
	$scope.doEdit = function(){
		$http.put(recordUrl, $scope.record).then(
			function success(){
				$location.path("/records");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
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
