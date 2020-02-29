var wafepaApp = angular.module('wafepaApp');

wafepaApp.controller("UsersCtrl", function ($scope, $http, $location) {

    var url = "/api/users";

    $scope.users = [];
    $scope.naslov = "Users";

    var getUsers = function () {
        $http.get(url).then(
            function uspeh(result) {
                $scope.users = result.data;
            },
            function neuspeh(result) {
                alert("greska pri dobavljanju");
            }
        );
    };

    getUsers();

    $scope.doDelete = function (id) {
        $http.delete(url + "/" + id).then(
            function uspeh(result) {
                getUsers();
            },
            function neuspeh(result) {
                alert("neuspesno brisanje");
            }
        );
    };

    $scope.goToEdit = function (id) {
        $location.path("/users/edit/" + id);
    }

    $scope.goToAdd = function () {
        $location.path("/users/add");
    }

});

wafepaApp.controller("EditUserCtrl", function ($scope, $http, $location, $routeParams) {

    $scope.user = {};
    $scope.user.email = "";
    $scope.user.firstname = "";
    $scope.user.lastname = "";

    var getUser = function (id) {
        $http.get("/api/users/" + $routeParams.id).then(
            function uspeh(result) {
                $scope.user = result.data;
            },
            function neuspeh(result) {
                alert("neuspesno dobavljanje usera");
            }
        );
    };

    getUser();

    $scope.doEdit = function () {
        $http.put("/api/users/" + $routeParams.id, $scope.user).then(
            function uspeh(result) {
                $location.path("/users");
            },
            function neuspeh(result) {
                alert("neuspesno editovanje");
            }
        );
    }

});

wafepaApp.controller("AddUserCtrl", function ($scope, $http, $location) {

    $scope.user = {};
    $scope.user.email = "";
    $scope.user.firstname = "";
    $scope.user.lastname = "";
    $scope.user.password = "";
    $scope.user.passwordConfirm = "";

    $scope.doAdd = function () {console.log($scope.user);
        $http.post("/api/users", $scope.user).then(
            function uspeh(result) {
                $location.path("/users");
            },
            function neuspeh(result) {
                alert("neuspesno dodavanje");
            }
        );
    };

});