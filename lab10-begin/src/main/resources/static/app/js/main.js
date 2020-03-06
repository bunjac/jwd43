var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.controller("HomeCtrl", function ($scope) {
	$scope.message = "Hello JWD43!";
});


wafepaApp.controller("AddLinijaCtrl", function ($scope, $http, $location) {

	$scope.newLinija = {};
	$scope.newLinija.brojMesta = "";
	$scope.newLinija.cenaKarte = "";
	$scope.newLinija.destinacija = "";
	$scope.newLinija.vremePolaska = "";
	$scope.newLinija.prevoznikId = "";

	$scope.prevoznici = [];

	var getPrevoznici = function () {
		$http.get("/api/prevoznici").then(
			function uspeh(result) {
				$scope.prevoznici = result.data;
			},
			function neuspeh(result) {
				alert("neuspesno ucitavanje prevoznika");
			}
		);
	};

	getPrevoznici();

	$scope.doAdd = function () {
		$http.post("/api/linije", $scope.newLinija).then(
			function uspeh(result) {
				getLinije();
			},
			function neuspeh(result) {
				alert("neuspesno dodavanje");
			}
		);
	};


	$scope.linije = [];
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.opcije = ["1","10","20"];
	$scope.rowsPerPage = $scope.opcije[1];

	$scope.search = {};
	$scope.search.destinacija = "";
	$scope.search.prevoznikId = "";
	$scope.search.cenaKarte = "";

	var getLinije = function () {
		var config = { params: {} };
		if ($scope.destinacija != "") {
			config.params.destinacija = $scope.search.destinacija;
		}
		if ($scope.prevoznikId != "") {
			config.params.prevoznikId = $scope.search.prevoznikId;
		}
		if ($scope.cenaKarte != "") {
			config.params.maksCena = $scope.search.cenaKarte;
		}

		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;

		$http.get("/api/linije", config).then(
			function uspeh(result) {
				$scope.linije = result.data;
				$scope.totalPages = result.headers("totalPages");
			},
			function neuspeh(result) {
				alert("neuspesno ucitavanje linija");
			}
		);
	};

	getLinije();

	$scope.goToEdit = function (id) {
		$location.path("/linije/edit/" + id);
	}

	$scope.doSearch = function () {
		$scope.pageNum = 0;
		getLinije();
	}

	$scope.changePage = function(x){
		$scope.pageNum += x;
		getLinije();
	}

	$scope.Rezervisi = function(id){
		
	}

});

wafepaApp.controller("EditLinijaCtrl", function ($scope, $http, $routeParams, $location) {

	$scope.newLinija = {};
	$scope.newLinija.brojMesta = "";
	$scope.newLinija.cenaKarte = "";
	$scope.newLinija.destinacija = "";
	$scope.newLinija.vremePolaska = "";
	$scope.newLinija.prevoznikId = "";

	$scope.prevoznici = [];

	var getPrevoznici = function () {
		$http.get("/api/prevoznici").then(
			function uspeh(result) {
				$scope.prevoznici = result.data;
				getLinija();
				$scope.newLinija.brojMesta = "";
				$scope.newLinija.cenaKarte = "";
				$scope.newLinija.destinacija = "";
				$scope.newLinija.vremePolaska = "";
				$scope.newLinija.prevoznikId = "";
			},
			function neuspeh(result) {
				alert("neuspesno ucitavanje prevoznika");
			}
		);
	};

	getPrevoznici();


	var getLinija = function () {
		$http.get("/api/linije/" + $routeParams.id).then(
			function uspeh(result) {
				$scope.newLinija = result.data;
			},
			function neuspeh(result) {
				alert("neuspesno ucitavanje linija");
			}
		);
	};

	$scope.doSave = function () {
		$http.put("/api/linije/" + $routeParams.id, $scope.newLinija).then(
			function success() {
				$location.path("/linije");
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
		.when('/linije', {
			templateUrl: '/app/html/linije.html'
		})
		.when('/linije/edit/:id', {
			templateUrl: '/app/html/edit-linija.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
